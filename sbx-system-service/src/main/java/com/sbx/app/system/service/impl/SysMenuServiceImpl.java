package com.sbx.app.system.service.impl;

import com.sbx.app.system.dto.SysMenuDTO;
import com.sbx.app.system.dto.SysRoleDTO;
import com.sbx.app.system.dto.SysRoleMenuDTO;
import com.sbx.app.system.enums.MenuTypeEnum;
import com.sbx.app.system.params.ChangeSortParam;
import com.sbx.app.system.params.SysMenuParam;
import com.sbx.app.system.service.ISysMenuService;
import com.sbx.app.system.service.deal.SysMenuDeal;
import com.sbx.app.system.service.deal.SysRoleDeal;
import com.sbx.app.system.service.deal.SysRoleMenuDeal;
import com.sbx.app.system.service.query.SysMenuQuery;
import com.sbx.app.system.service.query.SysRoleQuery;
import com.sbx.core.model.base.result.PageResult;
import com.sbx.core.model.exception.CustomException;
import com.sbx.core.model.validator.Validator;
import com.sbx.core.tool.util.CollectionUtil;
import com.sbx.core.tool.util.ObjectUtils;
import com.sbx.core.tool.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

/**
 * <p>
 * 系统菜单 服务实现类
 * </p>
 *
 * @author Z.jc
 * @since 2021-06-26
 */
@Slf4j
@Service
public class SysMenuServiceImpl implements ISysMenuService {

    @Resource
    private SysMenuDeal sysMenuDeal;
    @Resource
    private SysRoleDeal sysRoleDeal;
    @Resource
    private SysRoleMenuDeal sysRoleMenuDeal;

    Map<String,Object> map = new HashMap<>();



    @Override
    public PageResult<SysMenuDTO> page(SysMenuParam param) {
        SysMenuQuery query = ObjectUtils.copy(param,SysMenuQuery.class);
        return sysMenuDeal.queryByCondition(query);
    }

    @Override
    public SysMenuDTO detailById(Long id) {
        return sysMenuDeal.queryById(id);
    }

    @Override
    public SysMenuDTO queryByRouter(String router,Integer ascription){
        return sysMenuDeal.queryByRouter(router,ascription);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long create(SysMenuDTO sysMenuDTO) {
        Validator.getInstance()
                .notNull(sysMenuDTO.getMenuLevel() > 1,sysMenuDTO.getParentId(),"parentId")
                .notBlank(sysMenuDTO.getMenuTitle(),"menuTitle")
                .notBlank(sysMenuDTO.getMenuCode(),"menuCode")
                .notNull(sysMenuDTO.getMenuType(),"menuType")
                .notNull(sysMenuDTO.getAscription(),"ascription")
                .notNull(sysMenuDTO.getMenuLevel(),"menuLevel");
        if (sysMenuDTO.getMenuLevel() < 1 || sysMenuDTO.getMenuLevel() > 4) {
            throw new CustomException("菜单等级错误,等级最小1 最大4");
        }
        if (Objects.equals(sysMenuDTO.getMenuType(), MenuTypeEnum.MENU.getCode())) {
            SysMenuDTO oldSysMenu = sysMenuDeal.queryByAscriptionAndPath(sysMenuDTO.getAscription(),sysMenuDTO.getRouter());
            if (Objects.nonNull(oldSysMenu)) {
                throw new CustomException("path地址已存在");
            }
        }
        Long id = sysMenuDeal.create(sysMenuDTO);
        List<SysRoleDTO> sysRoles = sysRoleDeal.listSuperRole(sysMenuDTO.getAscription());
        if (CollectionUtil.isNotEmpty(sysRoles)) {
            List<SysRoleMenuDTO> roleMenuList = new ArrayList<>();
            sysRoles.forEach(role -> {
                SysRoleMenuDTO roleMenu = new SysRoleMenuDTO();
                roleMenu.setRoleId(role.getId());
                roleMenu.setMenuId(id);
                roleMenuList.add(roleMenu);
            });
            sysRoleMenuDeal.batchCreate(roleMenuList);
        }
        return id;
    }

    @Override
    public void update(SysMenuDTO sysMenuDTO) {
        Validator.getInstance()
                .notNull(sysMenuDTO.getId(),"id");
        sysMenuDTO.setAscription(null);
        SysMenuDTO oldSysMenu = sysMenuDeal.queryByAscriptionAndPath(sysMenuDTO.getAscription(),sysMenuDTO.getRouter());
        if (Objects.nonNull(oldSysMenu) && !Objects.equals(oldSysMenu.getId(),sysMenuDTO.getId())) {
            throw new CustomException("path地址已存在");
        }
        sysMenuDeal.update(sysMenuDTO);
    }

    @Override
    public Boolean changeSort(ChangeSortParam param){
        SysMenuDTO menu = sysMenuDeal.queryById(param.getCurrentId());
        SysMenuDTO changeMenu = sysMenuDeal.queryByChangeData(menu.getSort(),param.getSortType(),menu.getMenuLevel(),menu.getParentId(),menu.getAscription());
        if (Objects.isNull(changeMenu)) {
            throw new CustomException("已到顶");
        }
        Integer currentSort = menu.getSort();
        menu.setSort(changeMenu.getSort());
        changeMenu.setSort(currentSort);
        sysMenuDeal.update(menu);
        sysMenuDeal.update(changeMenu);
        return true;
    }

    @Override
    public Boolean delById(Long id) {
        return sysMenuDeal.delById(id);
    }

}
