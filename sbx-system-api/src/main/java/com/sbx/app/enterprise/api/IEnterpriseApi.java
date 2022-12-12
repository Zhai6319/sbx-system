package com.sbx.app.enterprise.api;

import com.sbx.app.enterprise.dto.EnterpriseDTO;
import com.sbx.app.enterprise.params.EnterpriseParam;
import com.sbx.core.model.api.Response;
import com.sbx.core.model.base.result.PageResult;


/**
 * <p>
 * 企业信息 dubbo接口
 * </p>
 *
 * @author Z.jc
 * @since 2022-10-02
 */
public interface IEnterpriseApi {

    /**
     * 条件分页查询企业信息
     * @param param 查询条件
     * @return      返回企业信息分页列表
     */
    Response<PageResult<EnterpriseDTO>> queryByCondition(EnterpriseParam param);

    /**
     * 根据id查询企业信息
     * @param id 数据id
     * @return   返回企业信息
     */
    Response<EnterpriseDTO> queryDetailById(Long id);

    /**
     * 创建企业信息
     * @param enterpriseDTO   企业信息数据
     * @return              返回创建数据id
     */
    Response<Long> create(EnterpriseDTO enterpriseDTO);

    /**
     * 修改企业信息
     * @param enterpriseDTO   企业信息数据
     * @return              返回结果
     */
    Response<Boolean> update(EnterpriseDTO enterpriseDTO);

    /**
     * 根据id删除企业信息
     * @param id    数据id
     * @return      返回删除结果
     */
    Response<Boolean> delById(Long id);

}
