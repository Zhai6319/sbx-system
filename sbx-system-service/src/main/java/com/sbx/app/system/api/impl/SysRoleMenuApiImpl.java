package com.sbx.app.system.api.impl;

import com.sbx.app.system.api.ISysRoleMenuApi;
import com.sbx.app.system.dto.SysRoleMenuDTO;
import com.sbx.app.system.params.SysRoleMenuParam;
import com.sbx.app.system.service.ISysRoleMenuService;
import com.sbx.core.model.api.Response;
import com.sbx.core.model.base.result.PageResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;

import javax.annotation.Resource;

/**
 * <p>
 * 角色菜单表 dubbo接口实现
 * </p>
 *
 * @author Z.jc
 * @since 2021-06-26
 */
@Slf4j
@DubboService
public class SysRoleMenuApiImpl implements ISysRoleMenuApi {

    @Resource
    private ISysRoleMenuService iSysRoleMenuService;

    @Override
    public Response<PageResult<SysRoleMenuDTO>> queryByCondition(SysRoleMenuParam param) {
        return Response.data(iSysRoleMenuService.page(param));
    }

    @Override
    public Response<SysRoleMenuDTO> queryDetailById(Long id) {
        return Response.data(iSysRoleMenuService.detailById(id));
    }

    @Override
    public Response<Long> create(SysRoleMenuDTO sysRoleMenuDTO) {
        return Response.data(iSysRoleMenuService.create(sysRoleMenuDTO));
    }

    @Override
    public Response<Boolean> update(SysRoleMenuDTO sysRoleMenuDTO) {
        iSysRoleMenuService.update(sysRoleMenuDTO);
        return Response.success("修改数据成功");
    }

    @Override
    public Response<Boolean> delById(Long id) {
        return Response.data(iSysRoleMenuService.delById(id));
    }

}
