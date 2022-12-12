package com.sbx.app.applet.api.impl;

import com.sbx.app.applet.api.IWebsiteAppEmpowerApi;
import com.sbx.app.applet.dto.WebsiteAppEmpowerDTO;
import com.sbx.app.applet.params.WebsiteAppEmpowerParam;
import com.sbx.app.applet.service.IWebsiteAppEmpowerService;
import com.sbx.core.model.api.Response;
import com.sbx.core.model.base.result.PageResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;

import javax.annotation.Resource;

/**
 * <p>
 * 域名应用授权 dubbo接口实现
 * </p>
 *
 * @author Z.jc
 * @since 2022-05-20
 */
@Slf4j
@DubboService
public class WebsiteAppEmpowerApiImpl implements IWebsiteAppEmpowerApi {

    @Resource
    private IWebsiteAppEmpowerService iWebsiteAppEmpowerService;

    @Override
    public Response<PageResult<WebsiteAppEmpowerDTO>> queryByCondition(WebsiteAppEmpowerParam param) {
        return Response.data(iWebsiteAppEmpowerService.page(param));
    }

    @Override
    public Response<WebsiteAppEmpowerDTO> queryDetailById(Long id) {
        return Response.data(iWebsiteAppEmpowerService.detailById(id));
    }

    @Override
    public Response<Long> create(WebsiteAppEmpowerDTO websiteAppEmpowerDTO) {
        return Response.data(iWebsiteAppEmpowerService.create(websiteAppEmpowerDTO));
    }

    @Override
    public Response<Boolean> update(WebsiteAppEmpowerDTO websiteAppEmpowerDTO) {
        iWebsiteAppEmpowerService.update(websiteAppEmpowerDTO);
        return Response.success("修改数据成功");
    }

    @Override
    public Response<Boolean> delById(Long id) {
        return Response.data(iWebsiteAppEmpowerService.delById(id));
    }

}
