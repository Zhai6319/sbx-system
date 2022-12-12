package com.sbx.app.enterprise.service.impl;

import com.sbx.app.enterprise.dto.EnterpriseDTO;
import com.sbx.app.enterprise.params.EnterpriseParam;
import com.sbx.app.enterprise.service.IEnterpriseService;
import com.sbx.app.enterprise.service.deal.EnterpriseDeal;
import com.sbx.app.enterprise.service.query.EnterpriseQuery;
import com.sbx.core.model.base.result.PageResult;
import com.sbx.core.tool.util.ObjectUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * 企业信息 服务实现类
 * </p>
 *
 * @author Z.jc
 * @since 2022-10-02
 */
@Slf4j
@Service
public class EnterpriseServiceImpl implements IEnterpriseService {

    @Resource
    private EnterpriseDeal enterpriseDeal;

    @Override
    public PageResult<EnterpriseDTO> page(EnterpriseParam param) {
        EnterpriseQuery query = ObjectUtils.copy(param,EnterpriseQuery.class);
        return enterpriseDeal.queryByCondition(query);
    }

    @Override
    public EnterpriseDTO detailById(Long id) {
        return enterpriseDeal.queryById(id);
    }

    @Override
    public Long create(EnterpriseDTO enterpriseDTO) {
        return enterpriseDeal.create(enterpriseDTO);
    }

    @Override
    public void update(EnterpriseDTO enterpriseDTO) {
        enterpriseDeal.update(enterpriseDTO);
    }

    @Override
    public Boolean delById(Long id) {
        return enterpriseDeal.delById(id);
    }

}
