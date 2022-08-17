package com.sbx.app.system.api.impl;

import com.sbx.app.system.api.ISysMenuApi;
import com.sbx.app.system.dto.SysMenuDTO;
import com.sbx.app.system.params.ChangeSortParam;
import com.sbx.app.system.params.SysMenuParam;
import com.sbx.app.system.service.ISysMenuService;
import com.sbx.core.model.api.Response;
import com.sbx.core.model.base.result.PageResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;

import javax.annotation.Resource;

/**
 * <p>
 * 系统菜单 dubbo接口实现
 * </p>
 *
 * @author Z.jc
 * @since 2021-06-26
 */
@Slf4j
@DubboService
public class SysMenuApiImpl implements ISysMenuApi {

    @Resource
    private ISysMenuService iSysMenuService;

    @Override
    public Response<PageResult<SysMenuDTO>> queryByCondition(SysMenuParam param) {
        return Response.data(iSysMenuService.page(param));
    }

    @Override
    public Response<SysMenuDTO> queryDetailById(Long id) {
        return Response.data(iSysMenuService.detailById(id));
    }

    @Override
    public Response<SysMenuDTO> queryByRouter(SysMenuParam param) {
        return Response.data(iSysMenuService.queryByRouter(param.getRouter(),param.getAscription()));
    }

    @Override
    public Response<Long> create(SysMenuDTO sysMenuDTO) {
        return Response.data(iSysMenuService.create(sysMenuDTO));
    }

    @Override
    public Response<Boolean> update(SysMenuDTO sysMenuDTO) {
        iSysMenuService.update(sysMenuDTO);
        return Response.success("修改数据成功");
    }

    @Override
    public Response<Boolean> changeSort(ChangeSortParam param) {
        return Response.data(iSysMenuService.changeSort(param));
    }

    @Override
    public Response<Boolean> delById(Long id) {
        return Response.data(iSysMenuService.delById(id));
    }

}
