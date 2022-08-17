package com.sbx.app.system.api.impl;

import com.sbx.app.system.api.ISysTagApi;
import com.sbx.app.system.dto.SysTagDTO;
import com.sbx.app.system.params.SysTagParam;
import com.sbx.app.system.service.ISysTagService;
import com.sbx.core.model.api.Response;
import com.sbx.core.model.base.result.PageResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;

import javax.annotation.Resource;

/**
 * <p>
 * 系统标签 dubbo接口实现
 * </p>
 *
 * @author Z.jc
 * @since 2021-07-16
 */
@Slf4j
@DubboService
public class SysTagApiImpl implements ISysTagApi {

    @Resource
    private ISysTagService iSysTagService;

    @Override
    public Response<PageResult<SysTagDTO>> queryByCondition(SysTagParam param) {
        return Response.data(iSysTagService.page(param));
    }

    @Override
    public Response<SysTagDTO> queryDetailById(Long id) {
        return Response.data(iSysTagService.detailById(id));
    }

    @Override
    public Response<Long> create(SysTagDTO sysTagDTO) {
        return Response.data(iSysTagService.create(sysTagDTO));
    }

    @Override
    public Response<Boolean> update(SysTagDTO sysTagDTO) {
        iSysTagService.update(sysTagDTO);
        return Response.success("修改数据成功");
    }

    @Override
    public Response<Boolean> delById(Long id) {
        return Response.data(iSysTagService.delById(id));
    }

}
