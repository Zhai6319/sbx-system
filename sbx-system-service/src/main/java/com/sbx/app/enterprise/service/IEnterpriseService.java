package com.sbx.app.enterprise.service;

import com.sbx.app.enterprise.dto.EnterpriseDTO;
import com.sbx.app.enterprise.params.EnterpriseParam;
import com.sbx.core.model.base.result.PageResult;


/**
 * <p>
 * 企业信息 服务类
 * </p>
 *
 * @author Z.jc
 * @since 2022-10-02
 */
public interface IEnterpriseService {

    /**
     * 分页查询企业信息
     * @param param 查询条件
     * @return      返回分页列表
     */
    PageResult<EnterpriseDTO> page(EnterpriseParam param);

   /**
    * 根据id获取企业信息详情
    * @param id    数据id
    * @return      返回企业信息详情
    */
   EnterpriseDTO detailById(Long id);

    /**
     * 创建企业信息
     * @param enterpriseDTO   企业信息数据
     * @return              返回创建数据id
     */
    Long create(EnterpriseDTO enterpriseDTO);

    /**
     * 修改企业信息
     * @param enterpriseDTO   企业信息数据
     */
    void update(EnterpriseDTO enterpriseDTO);

    /**
     * 根据id删除企业信息
     * @param id    数据id
     * @return      返回删除结果
     */
    Boolean delById(Long id);

}
