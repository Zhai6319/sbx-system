package com.sbx.app.enterprise.api.impl;

import com.sbx.app.enterprise.api.IEnterpriseApi;
import com.sbx.app.enterprise.dto.EnterpriseDTO;
import com.sbx.app.enterprise.params.EnterpriseParam;
import com.sbx.app.enterprise.service.IEnterpriseService;
import com.sbx.core.model.api.Response;
import com.sbx.core.model.base.result.PageResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;

import javax.annotation.Resource;

/**
 * <p>
 * 企业信息 dubbo接口实现
 * </p>
 *
 * @author Z.jc
 * @since 2022-10-02
 */
@Slf4j
@DubboService
public class EnterpriseApiImpl implements IEnterpriseApi {

    @Resource
    private IEnterpriseService iEnterpriseService;

    @Override
    public Response<PageResult<EnterpriseDTO>> queryByCondition(EnterpriseParam param) {
        return Response.data(iEnterpriseService.page(param));
    }

    @Override
    public Response<EnterpriseDTO> queryDetailById(Long id) {
        return Response.data(iEnterpriseService.detailById(id));
    }

    @Override
    public Response<Long> create(EnterpriseDTO enterpriseDTO) {
        return Response.data(iEnterpriseService.create(enterpriseDTO));
    }

    @Override
    public Response<Boolean> update(EnterpriseDTO enterpriseDTO) {
        iEnterpriseService.update(enterpriseDTO);
        return Response.success("修改数据成功");
    }

    @Override
    public Response<Boolean> delById(Long id) {
        return Response.data(iEnterpriseService.delById(id));
    }

}
