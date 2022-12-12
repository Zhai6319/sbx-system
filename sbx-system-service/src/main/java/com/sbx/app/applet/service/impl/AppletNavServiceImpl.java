package com.sbx.app.applet.service.impl;

import com.sbx.app.applet.dto.AppletNavDTO;
import com.sbx.app.applet.params.AppletNavParam;
import com.sbx.app.applet.service.IAppletNavService;
import com.sbx.app.applet.service.deal.AppletNavDeal;
import com.sbx.app.applet.service.query.AppletNavQuery;
import com.sbx.core.model.base.result.PageResult;
import com.sbx.core.tool.util.ObjectUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * 小程序菜单栏 服务实现类
 * </p>
 *
 * @author Z.jc
 * @since 2022-03-05
 */
@Slf4j
@Service
public class AppletNavServiceImpl implements IAppletNavService {

    @Resource
    private AppletNavDeal appletNavDeal;

    @Override
    public PageResult<AppletNavDTO> page(AppletNavParam param) {
        AppletNavQuery query = ObjectUtils.copy(param,AppletNavQuery.class);
        return appletNavDeal.queryByCondition(query);
    }

    @Override
    public AppletNavDTO detailById(Long id) {
        return appletNavDeal.queryById(id);
    }

    @Override
    public Long create(AppletNavDTO appletNavDTO) {
        return appletNavDeal.create(appletNavDTO);
    }

    @Override
    public void update(AppletNavDTO appletNavDTO) {
        appletNavDeal.update(appletNavDTO);
    }

    @Override
    public Boolean delById(Long id) {
        return appletNavDeal.delById(id);
    }

}
