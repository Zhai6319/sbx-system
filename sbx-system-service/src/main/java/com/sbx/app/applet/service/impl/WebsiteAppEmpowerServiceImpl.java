package com.sbx.app.applet.service.impl;

import com.sbx.app.applet.dto.WebsiteAppEmpowerDTO;
import com.sbx.app.applet.params.WebsiteAppEmpowerParam;
import com.sbx.app.applet.service.IWebsiteAppEmpowerService;
import com.sbx.app.applet.service.deal.WebsiteAppEmpowerDeal;
import com.sbx.app.applet.service.query.WebsiteAppEmpowerQuery;
import com.sbx.core.model.base.result.PageResult;
import com.sbx.core.tool.util.ObjectUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * 域名应用授权 服务实现类
 * </p>
 *
 * @author Z.jc
 * @since 2022-05-20
 */
@Slf4j
@Service
public class WebsiteAppEmpowerServiceImpl implements IWebsiteAppEmpowerService {

    @Resource
    private WebsiteAppEmpowerDeal websiteAppEmpowerDeal;

    @Override
    public PageResult<WebsiteAppEmpowerDTO> page(WebsiteAppEmpowerParam param) {
        WebsiteAppEmpowerQuery query = ObjectUtils.copy(param,WebsiteAppEmpowerQuery.class);
        return websiteAppEmpowerDeal.queryByCondition(query);
    }

    @Override
    public WebsiteAppEmpowerDTO detailById(Long id) {
        return websiteAppEmpowerDeal.queryById(id);
    }

    @Override
    public Long create(WebsiteAppEmpowerDTO websiteAppEmpowerDTO) {
        return websiteAppEmpowerDeal.create(websiteAppEmpowerDTO);
    }

    @Override
    public void update(WebsiteAppEmpowerDTO websiteAppEmpowerDTO) {
        websiteAppEmpowerDeal.update(websiteAppEmpowerDTO);
    }

    @Override
    public Boolean delById(Long id) {
        return websiteAppEmpowerDeal.delById(id);
    }

}
