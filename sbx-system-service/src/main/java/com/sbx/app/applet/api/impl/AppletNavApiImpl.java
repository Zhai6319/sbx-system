package com.sbx.app.applet.api.impl;

import com.sbx.app.applet.api.IAppletNavApi;
import com.sbx.app.applet.dto.AppletNavDTO;
import com.sbx.app.applet.params.AppletNavParam;
import com.sbx.app.applet.service.IAppletNavService;
import com.sbx.core.model.api.Response;
import com.sbx.core.model.base.result.PageResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;

import javax.annotation.Resource;

/**
 * <p>
 * 小程序菜单栏 dubbo接口实现
 * </p>
 *
 * @author Z.jc
 * @since 2022-03-05
 */
@Slf4j
@DubboService
public class AppletNavApiImpl implements IAppletNavApi {

    @Resource
    private IAppletNavService iAppletNavService;

    @Override
    public Response<PageResult<AppletNavDTO>> queryByCondition(AppletNavParam param) {
        return Response.data(iAppletNavService.page(param));
    }

    @Override
    public Response<AppletNavDTO> queryDetailById(Long id) {
        return Response.data(iAppletNavService.detailById(id));
    }

    @Override
    public Response<Long> create(AppletNavDTO appletNavDTO) {
        return Response.data(iAppletNavService.create(appletNavDTO));
    }

    @Override
    public Response<Boolean> update(AppletNavDTO appletNavDTO) {
        iAppletNavService.update(appletNavDTO);
        return Response.success("修改数据成功");
    }

    @Override
    public Response<Boolean> delById(Long id) {
        return Response.data(iAppletNavService.delById(id));
    }

}
