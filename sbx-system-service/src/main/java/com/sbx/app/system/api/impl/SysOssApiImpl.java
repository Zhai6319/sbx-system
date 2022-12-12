package com.sbx.app.system.api.impl;


import com.sbx.app.system.api.ISysOssApi;
import com.sbx.app.system.dto.StsDTO;
import com.sbx.app.system.service.ISysOssService;
import com.sbx.core.model.api.Response;
import org.apache.dubbo.config.annotation.DubboService;

import javax.annotation.Resource;

/**
 * <p>说明：</p>
 *
 * @author Z.jc
 * @version 1.0.0
 * @since 2021/1/7
 */
@DubboService
public class SysOssApiImpl implements ISysOssApi {

    @Resource
    private ISysOssService iSysOssService;

    @Override
    public Response<StsDTO> sts(String fileName) {
        return Response.data(iSysOssService.sts(fileName));
    }
}
