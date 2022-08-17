package com.sbx.app.system.api.impl;

import com.sbx.app.system.api.ISysRoleApi;
import com.sbx.app.system.dto.SysRoleDTO;
import com.sbx.app.system.params.SysRoleParam;
import com.sbx.app.system.service.ISysRoleService;
import com.sbx.core.model.api.Response;
import com.sbx.core.model.base.result.PageResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;

import javax.annotation.Resource;

/**
 * <p>
 * 系统角色 dubbo接口实现
 * </p>
 *
 * @author Z.jc
 * @since 2021-06-26
 */
@Slf4j
@DubboService
public class SysRoleApiImpl implements ISysRoleApi {

    @Resource
    private ISysRoleService iSysRoleService;

    @Override
    public Response<PageResult<SysRoleDTO>> queryByCondition(SysRoleParam param) {
        return Response.data(iSysRoleService.page(param));
    }

    @Override
    public Response<SysRoleDTO> queryDetailById(Long id) {
        return Response.data(iSysRoleService.detailById(id));
    }

    @Override
    public Response<Long> create(SysRoleDTO sysRoleDTO) {
        return Response.data(iSysRoleService.create(sysRoleDTO));
    }

    @Override
    public Response<Boolean> update(SysRoleDTO sysRoleDTO) {
        iSysRoleService.update(sysRoleDTO);
        return Response.success("修改数据成功");
    }

    @Override
    public Response<Boolean> delById(Long id) {
        return Response.data(iSysRoleService.delById(id));
    }

}
