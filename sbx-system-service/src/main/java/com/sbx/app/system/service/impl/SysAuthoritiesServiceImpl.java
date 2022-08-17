package com.sbx.app.system.service.impl;

import com.sbx.app.system.dto.SysAuthoritiesDTO;
import com.sbx.app.system.params.SaveMenuAuthorityParam;
import com.sbx.app.system.params.SysAuthoritiesParam;
import com.sbx.app.system.service.ISysAuthoritiesService;
import com.sbx.app.system.service.deal.SysAuthoritiesDeal;
import com.sbx.app.system.service.query.SysAuthoritiesQuery;
import com.sbx.core.model.base.result.PageResult;
import com.sbx.core.model.validator.Validator;
import com.sbx.core.tool.util.CollectionUtil;
import com.sbx.core.tool.util.ObjectUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * 系统权限 服务实现类
 * </p>
 *
 * @author Z.jc
 * @since 2021-06-26
 */
@Slf4j
@Service
public class SysAuthoritiesServiceImpl implements ISysAuthoritiesService {

    @Resource
    private SysAuthoritiesDeal sysAuthoritiesDeal;

    @Override
    public PageResult<SysAuthoritiesDTO> page(SysAuthoritiesParam param) {
        SysAuthoritiesQuery query = ObjectUtils.copy(param,SysAuthoritiesQuery.class);
        return sysAuthoritiesDeal.queryByCondition(query);
    }

    @Override
    public SysAuthoritiesDTO detailById(Long id) {
        return sysAuthoritiesDeal.queryById(id);
    }

    @Override
    public Long create(SysAuthoritiesDTO sysAuthoritiesDTO) {
        return sysAuthoritiesDeal.create(sysAuthoritiesDTO);
    }

    @Override
    public Boolean batchCreate(SaveMenuAuthorityParam param) {
        Validator.getInstance()
                .notNull(param.getMenuId(),"menuId")
                .notBlank(param.getModule(),"module");
        sysAuthoritiesDeal.deleteByMenuId(param.getMenuId(),param.getModule());
        if (CollectionUtil.isNotEmpty(param.getSysAuthorities())) {
            for (SysAuthoritiesDTO sysAuthoritiesDTO : param.getSysAuthorities()) {
                Validator.getInstance().notNull(sysAuthoritiesDTO.getMenuId(),"存在空menuId")
                        .notBlank(sysAuthoritiesDTO.getAuthority(),"存在空 authority")
                        .notBlank(sysAuthoritiesDTO.getMethod(),"存在空 method")
                        .notNull(sysAuthoritiesDTO.getAuthorityType(),"存在空type");
            }
        }
        return sysAuthoritiesDeal.batchCreateAuthority(param.getSysAuthorities());
    }

    @Override
    public void update(SysAuthoritiesDTO sysAuthoritiesDTO) {
        sysAuthoritiesDeal.update(sysAuthoritiesDTO);
    }

    @Override
    public Boolean delById(Long id) {
        return sysAuthoritiesDeal.delById(id);
    }

}
