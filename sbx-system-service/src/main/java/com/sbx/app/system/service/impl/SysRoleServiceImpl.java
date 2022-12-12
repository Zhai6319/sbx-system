package com.sbx.app.system.service.impl;

import com.sbx.app.system.dto.SysRoleDTO;
import com.sbx.app.system.dto.SysRoleMenuDTO;
import com.sbx.app.system.params.SysRoleParam;
import com.sbx.app.system.service.ISysRoleService;
import com.sbx.app.system.service.deal.SysRoleDeal;
import com.sbx.app.system.service.deal.SysRoleMenuDeal;
import com.sbx.app.system.service.query.SysRoleQuery;
import com.sbx.core.model.base.result.PageResult;
import com.sbx.core.model.exception.CustomException;
import com.sbx.core.model.validator.Validator;
import com.sbx.core.tool.util.CollectionUtil;
import com.sbx.core.tool.util.ObjectUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * <p>
 * 系统角色 服务实现类
 * </p>
 *
 * @author Z.jc
 * @since 2021-06-26
 */
@Slf4j
@Service
public class SysRoleServiceImpl implements ISysRoleService {

    @Resource
    private SysRoleDeal sysRoleDeal;
    @Resource
    private SysRoleMenuDeal sysRoleMenuDeal;

    @Override
    public PageResult<SysRoleDTO> page(SysRoleParam param) {
        SysRoleQuery query = ObjectUtils.copy(param,SysRoleQuery.class);
        return sysRoleDeal.queryByCondition(query);
    }

    @Override
    public SysRoleDTO detailById(Long id) {
        SysRoleDTO role = sysRoleDeal.queryById(id);
        List<SysRoleMenuDTO> roleMenus = sysRoleMenuDeal.queryByRoleId(id);
        List<Long> menuIds = roleMenus.stream().filter(w -> !w.getHalfFlag()).map(SysRoleMenuDTO::getMenuId).collect(Collectors.toList());
        List<Long> halfMenuIds = roleMenus.stream().filter(SysRoleMenuDTO::getHalfFlag).map(SysRoleMenuDTO::getMenuId).collect(Collectors.toList());
        role.setMenuIds(menuIds);
        role.setHalfMenuIds(halfMenuIds);
        return role;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long create(SysRoleDTO sysRoleDTO) {
        Validator.getInstance()
                .notNull(sysRoleDTO.getRoleName(),"roleName")
                .notNull(sysRoleDTO.getRoleType(),"roleType")
                .notEmpty(sysRoleDTO.getMenuIds(),"menuIds");
        Long roleId = sysRoleDeal.create(sysRoleDTO);
        this.saveRoleMenu(roleId, sysRoleDTO.getMenuIds(), sysRoleDTO.getHalfMenuIds());
        return roleId;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(SysRoleDTO sysRoleDTO) {
        Validator.getInstance()
                .notNull(sysRoleDTO.getId(),"id")
                .notNull(sysRoleDTO.getRoleName(),"roleName")
                .notEmpty(sysRoleDTO.getMenuIds(),"menuIds");
        SysRoleDTO oldRole = sysRoleDeal.queryById(sysRoleDTO.getId());
        if (oldRole.getSuperFlag()) {
            throw new CustomException("超级管理员角色不可修改");
        }
        sysRoleDTO.setRoleType(null);
        sysRoleDeal.update(sysRoleDTO);
        sysRoleMenuDeal.delByRoleId(sysRoleDTO.getId());
        this.saveRoleMenu(sysRoleDTO.getId(),sysRoleDTO.getMenuIds(),sysRoleDTO.getHalfMenuIds());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean delById(Long id) {
        SysRoleDTO oldRole = sysRoleDeal.queryById(id);
        if (oldRole.getSuperFlag()) {
            throw new CustomException("超级管理员角色不可删除");
        }
        sysRoleMenuDeal.delByRoleId(id);
        return sysRoleDeal.delById(id);
    }

    /**
     * 保存角色菜单
     * @param roleId    角色id
     * @param menuIds   菜单id
     * @param halfMenuIds   半联动菜单id
     */
    private void saveRoleMenu(Long roleId, List<Long> menuIds, List<Long> halfMenuIds) {
        List<SysRoleMenuDTO> roleMenuList = new ArrayList<>();
        if (CollectionUtil.isNotEmpty(menuIds)) {
            menuIds.forEach(menuId->{
                SysRoleMenuDTO roleMenu = new SysRoleMenuDTO();
                roleMenu.setRoleId(roleId);
                roleMenu.setMenuId(menuId);
                roleMenu.setHalfFlag(false);
                roleMenuList.add(roleMenu);
            });
        }
        if (CollectionUtil.isNotEmpty(halfMenuIds)) {
            halfMenuIds.forEach(menuId->{
                SysRoleMenuDTO roleMenu = new SysRoleMenuDTO();
                roleMenu.setRoleId(roleId);
                roleMenu.setMenuId(menuId);
                roleMenu.setHalfFlag(true);
                roleMenuList.add(roleMenu);
            });
        }
        if (CollectionUtil.isNotEmpty(roleMenuList)) {
            sysRoleMenuDeal.batchCreate(roleMenuList);
        }
    }

}
