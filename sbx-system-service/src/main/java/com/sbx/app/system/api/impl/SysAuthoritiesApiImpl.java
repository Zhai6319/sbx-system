package com.sbx.app.system.api.impl;

import com.sbx.app.system.api.ISysAuthoritiesApi;
import com.sbx.app.system.dto.SysAuthoritiesDTO;
import com.sbx.app.system.params.SaveMenuAuthorityParam;
import com.sbx.app.system.params.SysAuthoritiesParam;
import com.sbx.app.system.service.ISysAuthoritiesService;
import com.sbx.core.model.api.Response;
import com.sbx.core.model.base.result.PageResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;

import javax.annotation.Resource;

/**
 * <p>
 * 系统权限 dubbo接口实现
 * </p>
 *
 * @author Z.jc
 * @since 2021-06-26
 */
@Slf4j
@DubboService
public class SysAuthoritiesApiImpl implements ISysAuthoritiesApi {

    @Resource
    private ISysAuthoritiesService iSysAuthoritiesService;

    @Override
    public Response<PageResult<SysAuthoritiesDTO>> queryByCondition(SysAuthoritiesParam param) {
        return Response.data(iSysAuthoritiesService.page(param));
    }

    @Override
    public Response<SysAuthoritiesDTO> queryDetailById(Long id) {
        return Response.data(iSysAuthoritiesService.detailById(id));
    }

    @Override
    public Response<Long> create(SysAuthoritiesDTO sysAuthoritiesDTO) {
        return Response.data(iSysAuthoritiesService.create(sysAuthoritiesDTO));
    }

    @Override
    public Response<Boolean> batchCreate(SaveMenuAuthorityParam param) {
        return Response.data(iSysAuthoritiesService.batchCreate(param));
    }

    @Override
    public Response<Boolean> update(SysAuthoritiesDTO sysAuthoritiesDTO) {
        iSysAuthoritiesService.update(sysAuthoritiesDTO);
        return Response.success("修改数据成功");
    }

    @Override
    public Response<Boolean> delById(Long id) {
        return Response.data(iSysAuthoritiesService.delById(id));
    }

}
