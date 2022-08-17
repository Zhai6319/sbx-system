package com.sbx.app.log.service.deal;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sbx.app.log.dataobject.Log;
import com.sbx.app.log.dto.LogDTO;
import com.sbx.app.log.mapper.LogMapper;
import com.sbx.app.log.service.query.LogQuery;
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
 * 日志记录 数据处理
 * </p>
 *
 * @author Z.jc
 * @since 2021-10-29
 */
@Service
public class LogDeal extends BaseServiceImpl<LogMapper, Log>  {


    /**
    * 条件分页查询数据
    * @param query 查询条件
    * @return      返回分页列表
    */
    public PageResult<LogDTO> queryByCondition(LogQuery query){
        Page<Log> page = new Page<>(query.getCurrent(),query.getSize());
        LambdaQueryWrapper<Log> wrapper = this.buildWrapper(query);
        Page<Log> doPage = super.page(page,wrapper);

        List<LogDTO> dataList = ObjectUtils.copyList(doPage.getRecords(), LogDTO.class);
        PageResult<LogDTO> pageResult = new PageResult<>();
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
    public LogDTO queryById(Long id){
        Log log = baseMapper.selectById(id);
        return ObjectUtils.copy(log,LogDTO.class);
    }

    /**
     * 创建日志记录
     * @param logDTO   日志记录
     * @return              返回创建ID
     */
    public Long create(LogDTO logDTO){
        Log log = ObjectUtils.copy(logDTO,Log.class);
        if (!super.save(log)) {
            throw new CustomException(EResultCode.FAILURE,"创建日志记录失败");
        }
        logDTO.setId(log.getId());
        return logDTO.getId();
    }

    /**
     * 修改日志记录
     * @param logDTO   日志记录
     */
    public void update(LogDTO logDTO){
        Log log = ObjectUtils.copy(logDTO,Log.class);
            log.setUpdateTime(null);
            if (!super.updateById(log)) {
            throw new CustomException(EResultCode.FAILURE,"修改日志记录失败");
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
    private LambdaQueryWrapper<Log> buildWrapper(LogQuery query){
        LambdaQueryWrapper<Log> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(Objects.nonNull(query.getUserId()),Log::getUserId,query.getUserId());
        wrapper.eq(StringUtil.isNotBlank(query.getLogTitle()),Log::getLogTitle,query.getLogTitle());
        wrapper.eq(StringUtil.isNotBlank(query.getParams()),Log::getParams,query.getParams());
        wrapper.eq(StringUtil.isNotBlank(query.getContent()),Log::getContent,query.getContent());
        wrapper.eq(Objects.nonNull(query.getLogType()),Log::getLogType,query.getLogType());
        wrapper.eq(StringUtil.isNotBlank(query.getMethod()),Log::getMethod,query.getMethod());
        wrapper.eq(StringUtil.isNotBlank(query.getMethodClass()),Log::getMethodClass,query.getMethodClass());
        wrapper.eq(StringUtil.isNotBlank(query.getMethodName()),Log::getMethodName,query.getMethodName());
        wrapper.eq(StringUtil.isNotBlank(query.getRemoteIp()),Log::getRemoteIp,query.getRemoteIp());
        wrapper.eq(StringUtil.isNotBlank(query.getRequestUri()),Log::getRequestUri,query.getRequestUri());
        return wrapper;
    }

}