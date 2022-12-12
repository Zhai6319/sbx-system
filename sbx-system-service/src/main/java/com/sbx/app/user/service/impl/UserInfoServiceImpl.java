package com.sbx.app.user.service.impl;

import com.google.common.collect.Lists;
import com.sbx.app.system.dto.SysMenuDTO;
import com.sbx.app.system.dto.SysRoleMenuDTO;
import com.sbx.app.system.enums.MenuTypeEnum;
import com.sbx.app.user.dto.*;
import com.sbx.app.user.enums.UserTypeEnum;
import com.sbx.app.user.params.UserInfoParam;
import com.sbx.app.user.params.UserSaveParam;
import com.sbx.app.user.repository.AuthorityRepository;
import com.sbx.app.user.repository.MenuRepository;
import com.sbx.app.user.repository.RoleRepository;
import com.sbx.app.user.service.IUserInfoService;
import com.sbx.app.user.service.deal.UserInfoDeal;
import com.sbx.app.user.service.deal.UserRoleDeal;
import com.sbx.app.user.service.executor.user.create.CreateUserExecutorRegister;
import com.sbx.app.user.service.executor.user.create.model.CreateUserModel;
import com.sbx.app.user.service.executor.user.update.UpdateUserExecutorRegister;
import com.sbx.app.user.service.executor.user.update.model.UpdateUserModel;
import com.sbx.app.user.service.query.UserInfoQuery;
import com.sbx.common.auth.TokenHelper;
import com.sbx.common.auth.model.AuthUser;
import com.sbx.core.model.base.dto.BaseDTO;
import com.sbx.core.model.base.result.PageResult;
import com.sbx.core.model.exception.AuthorityException;
import com.sbx.core.model.exception.CustomException;
import com.sbx.core.model.validator.Validator;
import com.sbx.core.tool.util.CollectionUtil;
import com.sbx.core.tool.util.ObjectUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * <p>
 * 用户信息 服务实现类
 * </p>
 *
 * @author Z.jc
 * @since 2021-06-26
 */
@Slf4j
@Service
public class UserInfoServiceImpl implements IUserInfoService {

    @Resource
    private UserInfoDeal userInfoDeal;
    @Resource
    private UserRoleDeal userRoleDeal;
    @Resource
    private RoleRepository roleRepository;
    @Resource
    private MenuRepository menuRepository;
    @Resource
    private TokenHelper tokenHelper;
    @Resource
    private AuthorityRepository authorityRepository;

    @Override
    public PageResult<UserInfoDetailDTO> page(UserInfoParam param) {
        UserInfoQuery query = ObjectUtils.copy(param,UserInfoQuery.class);
        PageResult<UserInfoDTO> userPage = userInfoDeal.queryByCondition(query);
        Map<Long,List<UserRoleDTO>> userRoleGroupMap = userRoleDeal.groupMapByUserIds(userPage.getRecords().stream().map(BaseDTO::getId).collect(Collectors.toList()));
        return ObjectUtils.copyPage(userPage,UserInfoDetailDTO.class,((user, userDetail) -> {
            List<UserRoleDTO> roleList = userRoleGroupMap.get(user.getId());
            if (CollectionUtil.isEmpty(roleList)) {
                roleList = Collections.emptyList();
            }
            userDetail.setRoleIds(roleList.stream().map(UserRoleDTO::getRoleId).collect(Collectors.toList()));
        }));
    }

    @Override
    public PageResult<UserInfoDTO> queryByCondition(UserInfoParam param) {
        UserInfoQuery query = ObjectUtils.copy(param,UserInfoQuery.class);
        return userInfoDeal.queryByCondition(query);
    }

    @Override
    public UserInfoDTO detailById(Long id) {
        return userInfoDeal.queryById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long create(UserSaveParam param) {
        param.checkParams();
        CreateUserModel model = new CreateUserModel();
        model.setUserInfo(param.getUserInfo());
        model.setPassword(param.getPassword());
        model.setUnionId(param.getUnionId());
        model.setLoginType(param.getLoginType());
        model.setSecretKey(param.getSecretKey());
        model.setRoleIds(param.getRoleIds());
        return CreateUserExecutorRegister.findByType(UserTypeEnum.findByType(param.getUserInfo().getUserType())).doExecute(model);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(UserSaveParam param) {
        Validator.getInstance()
                .notNull(param.getUserInfo(),"userInfo")
                .notNull(param.getUserInfo().getId(),"id");
        UserInfoDTO userInfo = userInfoDeal.queryById(param.getUserInfo().getId());
        UpdateUserModel model = new UpdateUserModel();
        model.setUserInfo(param.getUserInfo());
        model.setRoleIds(param.getRoleIds());
        UpdateUserExecutorRegister.findByType(UserTypeEnum.findByType(userInfo.getUserType())).doExecute(model);
    }

    @Override
    public Boolean delById(Long id) {
        return userInfoDeal.delById(id);
    }

    @Override
    public List<UserNavigationBarDTO> userNavigationBarList(UserInfoParam param){
        Validator.getInstance().notNull(param.getId(),"userId");
        //获取用户角色
        List<UserRoleDTO> userRoles = userRoleDeal.listByUserId(param.getId());
        if (CollectionUtil.isEmpty(userRoles)) {
            return Collections.emptyList();
        }
        List<Long> roleIds = userRoles.stream().map(UserRoleDTO::getRoleId).collect(Collectors.toList());
        //获取角色菜单
        List<SysRoleMenuDTO> roleMenus = roleRepository.roleMenuListByRoleIds(roleIds);
        //获取菜单列表
        List<SysMenuDTO> menus = menuRepository.menuListByMenuIds(roleMenus.stream().map(SysRoleMenuDTO::getMenuId).collect(Collectors.toList()));
        if (CollectionUtil.isEmpty(menus)) {
            return Collections.emptyList();
        }
        List<SysMenuDTO> buttons = menus.stream().filter(a -> Objects.equals(a.getMenuType(), MenuTypeEnum.BUTTON.getCode())).collect(Collectors.toList());
        List<SysMenuDTO> navigations = menus.stream().filter(a -> Objects.equals(a.getMenuType(),MenuTypeEnum.MENU.getCode())).collect(Collectors.toList());
        Map<Long,List<SysMenuDTO>> buttonGroupMap = buttons.stream().collect(Collectors.groupingBy(SysMenuDTO::getParentId));
        return this.buildUserNavRouters(navigations,buttonGroupMap);
    }


    /**
     * 获取用户路由关系链
     * @param userId    用户id
     * @param path  当前路由
     * @return  返回关系列表
     */
    public List<UserNavigationBarDTO> userRouter(Long userId, String path) {
        List<Long> userMenuIds = this.getMenuIds(userId);
        SysMenuDTO sysMenu = this.checkAndGetMenu(userId,path,userMenuIds);
        //构建关联路由菜单
        List<SysMenuDTO> routerMenus = this.buildRouterMenu(sysMenu,Lists.newArrayList());
        Map<Long, List<SysMenuDTO>> buttonGroupMap = menuRepository.buttonMapBuParentIds(routerMenus.stream().map(BaseDTO::getId).collect(Collectors.toList()),userMenuIds);
        return this.buildUserNavRouters(routerMenus,buttonGroupMap);
    }

    public UserNavigationBarDTO router(Long userId,String path) {
        List<Long> userMenuIds = this.getMenuIds(userId);
        SysMenuDTO sysMenu = this.checkAndGetMenu(userId,path,userMenuIds);
        List<SysMenuDTO> buttonList = menuRepository.buttonsByParentId(sysMenu.getId(),userMenuIds);
        return this.buildUserNavRouter(sysMenu,buttonList);
    }

    @Override
    public UserInfoDTO userByToken(String token) {
        AuthUser authUser = tokenHelper.verificationToken(token);
        return userInfoDeal.queryById(authUser.getUserId());
    }

    @Override
    public Boolean checkUserAuthority(String authority,String method,Long userId){
        UserInfoDTO userInfo = userInfoDeal.queryById(userId);
        List<UserRoleDTO> userRoles = userRoleDeal.listByUserId(userId);
        List<SysRoleMenuDTO> roleMenus = roleRepository.roleMenuListByRoleIds(userRoles.stream().map(UserRoleDTO::getRoleId).collect(Collectors.toList()));
        List<Long> menuIds = roleMenus.stream().map(SysRoleMenuDTO::getMenuId).collect(Collectors.toList());
        return authorityRepository.userAuthorities( menuIds,authority,method,userInfo.getUserType());
    }

    private List<UserNavigationBarDTO> buildUserNavRouters(List<SysMenuDTO> navigations,Map<Long,List<SysMenuDTO>> buttonGroupMap){
        List<UserNavigationBarDTO> userNavigationBarList = Lists.newArrayList();
        for (SysMenuDTO navigation : navigations) {
            List<SysMenuDTO> menuButtons = buttonGroupMap.get(navigation.getId());
            UserNavigationBarDTO userNavigationBar = this.buildUserNavRouter(navigation,menuButtons);
            userNavigationBarList.add(userNavigationBar);
        }
        return userNavigationBarList;
    }

    private UserNavigationBarDTO buildUserNavRouter(SysMenuDTO navigation,List<SysMenuDTO> menuButtons){
        UserNavigationBarDTO userNavigationBar = new UserNavigationBarDTO();
        userNavigationBar.setComponent(navigation.getComponent());
        userNavigationBar.setId(navigation.getId());
        userNavigationBar.setRouter(navigation.getRouter());
        userNavigationBar.setRedirect(navigation.getRedirect());
        userNavigationBar.setName(navigation.getMenuCode());
        userNavigationBar.setParentId(navigation.getParentId());
        UserNavigationMetaDTO meta = new UserNavigationMetaDTO();
        meta.setIcon(navigation.getIcon());
        meta.setShow(navigation.getShowFlag());
        meta.setTitle(navigation.getMenuTitle());
        if (CollectionUtil.isNotEmpty(menuButtons)) {
            List<UserButtonDTO> userButtons = ObjectUtils.copyList(menuButtons,UserButtonDTO.class,(menuButton,userButton)->{
                userButton.setComponent(menuButton.getComponent());
                userButton.setTitle(menuButton.getMenuTitle());
                userButton.setPath(menuButton.getRouter());
                userButton.setName(menuButton.getMenuCode());
                userButton.setIcon(menuButton.getIcon());
            });
            meta.setButtons(userButtons);
        }
        userNavigationBar.setMeta(meta);
        return userNavigationBar;
    }

    public List<Long> getMenuIds(Long userId){
        List<UserRoleDTO> userRoles = userRoleDeal.listByUserId(userId);
        List<SysRoleMenuDTO> roleMenus = roleRepository.roleMenuListByRoleIds(userRoles.stream().map(UserRoleDTO::getRoleId).collect(Collectors.toList()));
        return roleMenus.stream().map(SysRoleMenuDTO::getMenuId).collect(Collectors.toList());
    }

    /**
     * 递归获取路由关系菜单
     * @param menu  当前菜单
     * @param menuList  关联菜单列表
     * @return  返回结果
     */
    private List<SysMenuDTO> buildRouterMenu(SysMenuDTO menu,List<SysMenuDTO> menuList){
        if (Objects.nonNull(menu)) {
            menuList.add(menu);
            if (Objects.nonNull(menu.getParentId()) && !Objects.equals(menu.getParentId(),0L)) {
                SysMenuDTO parentMenu = menuRepository.menuById(menu.getParentId());
                if (Objects.nonNull(parentMenu)) {
                    this.buildRouterMenu(parentMenu,menuList);
                }
            }
        }
        return menuList;
    }


    /**
     * 检查并获取菜单
     * @param userId    用户id
     * @param path  前端路由
     * @return  返回结果
     */
    private SysMenuDTO checkAndGetMenu(Long userId, String path, List<Long> userMenuIds){
        UserInfoDTO userInfo = userInfoDeal.queryById(userId);
        SysMenuDTO sysMenu = menuRepository.menuByRouter(path, userInfo.getUserType());
        if (Objects.isNull(sysMenu)) {
            return null;
        }
        if (userMenuIds.stream().noneMatch(menuId->Objects.equals(menuId,sysMenu.getId()))) {
            throw new AuthorityException("无访问权限");
        }
        return sysMenu;
    }

}
