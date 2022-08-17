package com.sbx.app.system.service.deal;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sbx.app.system.dataobject.SysTag;
import com.sbx.app.system.dto.SysTagDTO;
import com.sbx.app.system.mapper.SysTagMapper;
import com.sbx.app.system.service.query.SysTagQuery;
import com.sbx.core.model.base.result.PageResult;
import com.sbx.core.model.enums.EResultCode;
import com.sbx.core.model.exception.CustomException;
import com.sbx.core.mybatis.base.service.impl.BaseServiceImpl;
import com.sbx.core.tool.util.ObjectUtils;
import com.sbx.core.tool.util.StringUtil;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * <p>
 * 系统标签 数据处理
 * </p>
 *
 * @author Z.jc
 * @since 2021-07-16
 */
@Service
public class SysTagDeal extends BaseServiceImpl<SysTagMapper, SysTag>  {


    /**
    * 条件分页查询数据
    * @param query 查询条件
    * @return      返回分页列表
    */
    public PageResult<SysTagDTO> queryByCondition(SysTagQuery query){
        Page<SysTag> page = new Page<>(query.getCurrent(),query.getSize());
        LambdaQueryWrapper<SysTag> wrapper = this.buildWrapper(query);
        Page<SysTag> doPage = super.page(page,wrapper);

        List<SysTagDTO> dataList = ObjectUtils.copyList(doPage.getRecords(), SysTagDTO.class);
        PageResult<SysTagDTO> pageResult = new PageResult<>();
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
    public SysTagDTO queryById(Long id){
        SysTag sysTag = baseMapper.selectById(id);
        return ObjectUtils.copy(sysTag,SysTagDTO.class);
    }

    /**
     * 创建系统标签
     * @param sysTagDTO   系统标签
     * @return              返回创建ID
     */
    public Long create(SysTagDTO sysTagDTO){
        SysTag sysTag = ObjectUtils.copy(sysTagDTO,SysTag.class);
        if (!super.save(sysTag)) {
            throw new CustomException(EResultCode.FAILURE,"创建系统标签失败");
        }
        sysTagDTO.setId(sysTag.getId());
        return sysTagDTO.getId();
    }

    /**
     * 修改系统标签
     * @param sysTagDTO   系统标签
     */
    public void update(SysTagDTO sysTagDTO){
        SysTag sysTag = ObjectUtils.copy(sysTagDTO,SysTag.class);
            sysTag.setUpdateTime(null);
            if (!super.updateById(sysTag)) {
            throw new CustomException(EResultCode.FAILURE,"修改系统标签失败");
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
    private LambdaQueryWrapper<SysTag> buildWrapper(SysTagQuery query){
        LambdaQueryWrapper<SysTag> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(StringUtil.isNotBlank(query.getTagName()),SysTag::getTagName,query.getTagName());
        wrapper.eq(Objects.nonNull(query.getTagType()),SysTag::getTagType,query.getTagType());
        wrapper.eq(Objects.nonNull(query.getEnableFlag()),SysTag::getEnableFlag,query.getEnableFlag());
        return wrapper;
    }

}