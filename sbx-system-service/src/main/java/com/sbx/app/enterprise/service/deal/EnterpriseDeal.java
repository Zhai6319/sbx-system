package com.sbx.app.enterprise.service.deal;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sbx.app.enterprise.dataobject.Enterprise;
import com.sbx.app.enterprise.dto.EnterpriseDTO;
import com.sbx.app.enterprise.mapper.EnterpriseMapper;
import com.sbx.app.enterprise.service.query.EnterpriseQuery;
import com.sbx.core.model.base.result.PageResult;
import com.sbx.core.model.enums.EResultCode;
import com.sbx.core.model.exception.CustomException;
import com.sbx.core.mybatis.base.service.impl.BaseServiceImpl;
import com.sbx.core.tool.util.ObjectUtils;
import com.sbx.core.tool.util.StringUtil;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 企业信息 数据处理
 * </p>
 *
 * @author Z.jc
 * @since 2022-10-02
 */
@Service
public class EnterpriseDeal extends BaseServiceImpl<EnterpriseMapper, Enterprise>  {


    /**
    * 条件分页查询数据
    * @param query 查询条件
    * @return      返回分页列表
    */
    public PageResult<EnterpriseDTO> queryByCondition(EnterpriseQuery query){
        Page<Enterprise> page = new Page<>(query.getCurrent(),query.getSize());
        LambdaQueryWrapper<Enterprise> wrapper = this.buildWrapper(query);
        Page<Enterprise> doPage = super.page(page,wrapper);

        List<EnterpriseDTO> dataList = ObjectUtils.copyList(doPage.getRecords(), EnterpriseDTO.class);
        PageResult<EnterpriseDTO> pageResult = new PageResult<>();
        pageResult.setRecords(dataList);
        pageResult.setTotal(doPage.getTotal());
        pageResult.setSize(query.getSize());
        pageResult.setPages(doPage.getPages());
        pageResult.setCurrent(query.getCurrent());
        pageResult.setRecords(dataList);
        return pageResult;
    }

    /**
    * 根据id查询数据
    * @param id    数据id
    * @return      返回数据
    */
    public EnterpriseDTO queryById(Long id){
        Enterprise enterprise = baseMapper.selectById(id);
        return ObjectUtils.copy(enterprise,EnterpriseDTO.class);
    }

    /**
     * 创建企业信息
     * @param enterpriseDTO   企业信息
     * @return              返回创建ID
     */
    public Long create(EnterpriseDTO enterpriseDTO){
        Enterprise enterprise = ObjectUtils.copy(enterpriseDTO,Enterprise.class);
        if (!super.save(enterprise)) {
            throw new CustomException(EResultCode.FAILURE,"创建企业信息失败");
        }
        enterpriseDTO.setId(enterprise.getId());
        return enterpriseDTO.getId();
    }

    /**
     * 修改企业信息
     * @param enterpriseDTO   企业信息
     */
    public void update(EnterpriseDTO enterpriseDTO){
        Enterprise enterprise = ObjectUtils.copy(enterpriseDTO,Enterprise.class);
            enterprise.setUpdateTime(null);
            if (!super.updateById(enterprise)) {
            throw new CustomException(EResultCode.FAILURE,"修改企业信息失败");
        }
    }

    /**
     * 根据ID删除数据
     * @param id    数据ID
     * @return      返回删除结果
     */
    public Boolean delById(Long id){
        return super.removeById(id);
    }

   /**
    * 构建查询条件
    * @param query    查询条件
    * @return      构建sql包装
    */
    private LambdaQueryWrapper<Enterprise> buildWrapper(EnterpriseQuery query){
        LambdaQueryWrapper<Enterprise> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(StringUtil.isNotBlank(query.getEnterpriseName()),Enterprise::getEnterpriseName,query.getEnterpriseName());
        wrapper.eq(StringUtil.isNotBlank(query.getEnterpriseLogo()),Enterprise::getEnterpriseLogo,query.getEnterpriseLogo());
        wrapper.eq(StringUtil.isNotBlank(query.getLinkMan()),Enterprise::getLinkMan,query.getLinkMan());
        wrapper.eq(StringUtil.isNotBlank(query.getLinkMobile()),Enterprise::getLinkMobile,query.getLinkMobile());
        wrapper.eq(StringUtil.isNotBlank(query.getRemarks()),Enterprise::getRemarks,query.getRemarks());
        return wrapper;
    }

}