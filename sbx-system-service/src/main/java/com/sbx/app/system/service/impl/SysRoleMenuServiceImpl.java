package com.sbx.app.system.service.impl;

import com.sbx.app.system.dto.SysRoleMenuDTO;
import com.sbx.app.system.params.SysRoleMenuParam;
import com.sbx.app.system.service.ISysRoleMenuService;
import com.sbx.app.system.service.deal.SysRoleMenuDeal;
import com.sbx.app.system.service.query.SysRoleMenuQuery;
import com.sbx.core.model.base.result.PageResult;
import com.sbx.core.tool.util.ObjectUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * 角色菜单表 服务实现类
 * </p>
 *
 * @author Z.jc
 * @since 2021-06-26
 */
@Slf4j
@Service
public class SysRoleMenuServiceImpl implements ISysRoleMenuService {

    @Resource
    private SysRoleMenuDeal sysRoleMenuDeal;

    @Override
    public PageResult<SysRoleMenuDTO> page(SysRoleMenuParam param) {
        SysRoleMenuQuery query = ObjectUtils.copy(param,SysRoleMenuQuery.class);
        return sysRoleMenuDeal.queryByCondition(query);
    }

    @Override
    public SysRoleMenuDTO detailById(Long id) {
        return sysRoleMenuDeal.queryById(id);
    }

    @Override
    public Long create(SysRoleMenuDTO sysRoleMenuDTO) {
        return sysRoleMenuDeal.create(sysRoleMenuDTO);
    }

    @Override
    public void update(SysRoleMenuDTO sysRoleMenuDTO) {
        sysRoleMenuDeal.update(sysRoleMenuDTO);
    }

    @Override
    public Boolean delById(Long id) {
        return sysRoleMenuDeal.delById(id);
    }

}
