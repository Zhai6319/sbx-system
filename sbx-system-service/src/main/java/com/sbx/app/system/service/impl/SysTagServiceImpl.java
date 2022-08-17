package com.sbx.app.system.service.impl;

import com.sbx.app.system.dto.SysTagDTO;
import com.sbx.app.system.params.SysTagParam;
import com.sbx.app.system.service.ISysTagService;
import com.sbx.app.system.service.deal.SysTagDeal;
import com.sbx.app.system.service.query.SysTagQuery;
import com.sbx.core.model.base.result.PageResult;
import com.sbx.core.tool.util.ObjectUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * 系统标签 服务实现类
 * </p>
 *
 * @author Z.jc
 * @since 2021-07-16
 */
@Slf4j
@Service
public class SysTagServiceImpl implements ISysTagService {

    @Resource
    private SysTagDeal sysTagDeal;

    @Override
    public PageResult<SysTagDTO> page(SysTagParam param) {
        SysTagQuery query = ObjectUtils.copy(param,SysTagQuery.class);
        return sysTagDeal.queryByCondition(query);
    }

    @Override
    public SysTagDTO detailById(Long id) {
        return sysTagDeal.queryById(id);
    }

    @Override
    public Long create(SysTagDTO sysTagDTO) {
        return sysTagDeal.create(sysTagDTO);
    }

    @Override
    public void update(SysTagDTO sysTagDTO) {
        sysTagDeal.update(sysTagDTO);
    }

    @Override
    public Boolean delById(Long id) {
        return sysTagDeal.delById(id);
    }

}
