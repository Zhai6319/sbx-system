package com.sbx.app.applet.service.deal;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sbx.app.applet.dataobject.WebsiteAppEmpower;
import com.sbx.app.applet.dto.WebsiteAppEmpowerDTO;
import com.sbx.app.applet.mapper.WebsiteAppEmpowerMapper;
import com.sbx.app.applet.service.query.WebsiteAppEmpowerQuery;
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
 * 域名应用授权 数据处理
 * </p>
 *
 * @author Z.jc
 * @since 2022-05-20
 */
@Service
public class WebsiteAppEmpowerDeal extends BaseServiceImpl<WebsiteAppEmpowerMapper, WebsiteAppEmpower>  {


    /**
    * 条件分页查询数据
    * @param query 查询条件
    * @return      返回分页列表
    */
    public PageResult<WebsiteAppEmpowerDTO> queryByCondition(WebsiteAppEmpowerQuery query){
        Page<WebsiteAppEmpower> page = new Page<>(query.getCurrent(),query.getSize());
        LambdaQueryWrapper<WebsiteAppEmpower> wrapper = this.buildWrapper(query);
        Page<WebsiteAppEmpower> doPage = super.page(page,wrapper);

        List<WebsiteAppEmpowerDTO> dataList = ObjectUtils.copyList(doPage.getRecords(), WebsiteAppEmpowerDTO.class);
        PageResult<WebsiteAppEmpowerDTO> pageResult = new PageResult<>();
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
    public WebsiteAppEmpowerDTO queryById(Long id){
        WebsiteAppEmpower websiteAppEmpower = baseMapper.selectById(id);
        return ObjectUtils.copy(websiteAppEmpower,WebsiteAppEmpowerDTO.class);
    }

    /**
     * 创建域名应用授权
     * @param websiteAppEmpowerDTO   域名应用授权
     * @return              返回创建ID
     */
    public Long create(WebsiteAppEmpowerDTO websiteAppEmpowerDTO){
        WebsiteAppEmpower websiteAppEmpower = ObjectUtils.copy(websiteAppEmpowerDTO,WebsiteAppEmpower.class);
        if (!super.save(websiteAppEmpower)) {
            throw new CustomException(EResultCode.FAILURE,"创建域名应用授权失败");
        }
        websiteAppEmpowerDTO.setId(websiteAppEmpower.getId());
        return websiteAppEmpowerDTO.getId();
    }

    /**
     * 修改域名应用授权
     * @param websiteAppEmpowerDTO   域名应用授权
     */
    public void update(WebsiteAppEmpowerDTO websiteAppEmpowerDTO){
        WebsiteAppEmpower websiteAppEmpower = ObjectUtils.copy(websiteAppEmpowerDTO,WebsiteAppEmpower.class);
            websiteAppEmpower.setUpdateTime(null);
            if (!super.updateById(websiteAppEmpower)) {
            throw new CustomException(EResultCode.FAILURE,"修改域名应用授权失败");
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
    private LambdaQueryWrapper<WebsiteAppEmpower> buildWrapper(WebsiteAppEmpowerQuery query){
        LambdaQueryWrapper<WebsiteAppEmpower> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(StringUtil.isNotBlank(query.getDomainName()),WebsiteAppEmpower::getDomainName,query.getDomainName());
        wrapper.eq(StringUtil.isNotBlank(query.getAppId()),WebsiteAppEmpower::getAppId,query.getAppId());
        return wrapper;
    }

}