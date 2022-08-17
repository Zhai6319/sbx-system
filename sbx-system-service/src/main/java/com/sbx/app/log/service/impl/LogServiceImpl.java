package com.sbx.app.log.service.impl;

import com.sbx.app.log.dto.LogDTO;
import com.sbx.app.log.params.LogParam;
import com.sbx.app.log.service.ILogService;
import com.sbx.app.log.service.deal.LogDeal;
import com.sbx.app.log.service.query.LogQuery;
import com.sbx.core.model.base.result.PageResult;
import com.sbx.core.tool.util.ObjectUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * 日志记录 服务实现类
 * </p>
 *
 * @author Z.jc
 * @since 2021-10-29
 */
@Slf4j
@Service
public class LogServiceImpl implements ILogService {

    @Resource
    private LogDeal logDeal;

    @Override
    public PageResult<LogDTO> page(LogParam param) {
        LogQuery query = ObjectUtils.copy(param,LogQuery.class);
        return logDeal.queryByCondition(query);
    }

    @Override
    public LogDTO detailById(Long id) {
        return logDeal.queryById(id);
    }

    @Override
    public Long create(LogDTO logDTO) {
        return logDeal.create(logDTO);
    }

    @Override
    public void update(LogDTO logDTO) {
        logDeal.update(logDTO);
    }

    @Override
    public Boolean delById(Long id) {
        return logDeal.delById(id);
    }

}
