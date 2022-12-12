package com.sbx.app.log.api.impl;

import com.sbx.app.log.api.ILogApi;
import com.sbx.app.log.dto.LogDTO;
import com.sbx.app.log.params.LogParam;
import com.sbx.app.log.service.ILogService;
import com.sbx.core.model.api.Response;
import com.sbx.core.model.base.result.PageResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;

import javax.annotation.Resource;

/**
 * <p>
 * 日志记录 dubbo接口实现
 * </p>
 *
 * @author Z.jc
 * @since 2021-10-29
 */
@Slf4j
@DubboService
public class LogApiImpl implements ILogApi {

    @Resource
    private ILogService iLogService;

    @Override
    public Response<PageResult<LogDTO>> queryByCondition(LogParam param) {
        return Response.data(iLogService.page(param));
    }

    @Override
    public Response<LogDTO> queryDetailById(Long id) {
        return Response.data(iLogService.detailById(id));
    }

    @Override
    public Response<Long> create(LogDTO logDTO) {
        return Response.data(iLogService.create(logDTO));
    }

    @Override
    public Response<Boolean> update(LogDTO logDTO) {
        iLogService.update(logDTO);
        return Response.success("修改数据成功");
    }

    @Override
    public Response<Boolean> delById(Long id) {
        return Response.data(iLogService.delById(id));
    }

}
