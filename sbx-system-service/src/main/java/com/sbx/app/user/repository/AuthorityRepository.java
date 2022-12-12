package com.sbx.app.user.repository;

import com.alibaba.fastjson.JSONObject;
import com.sbx.app.system.api.ISysAuthoritiesApi;
import com.sbx.app.system.dto.SysAuthoritiesDTO;
import com.sbx.app.system.enums.AscriptionEnum;
import com.sbx.app.system.params.SysAuthoritiesParam;
import com.sbx.core.model.api.Response;
import com.sbx.core.model.base.result.PageResult;
import com.sbx.core.model.exception.CustomException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>说明：</p>
 *
 * @author Z.jc
 * @version 1.0.0
 * @since 2021/6/27
 */
@Slf4j
@Service
public class AuthorityRepository {

    @Resource
    private ISysAuthoritiesApi iSysAuthoritiesApi;

    /**
     * 根据菜单id列表获取菜单列表
     * @param menuIds   菜单id列表
     * @return          返回菜单列表
     */
    public Boolean userAuthorities(List<Long> menuIds, String authority, String method, Integer ascription){
        SysAuthoritiesParam param = new SysAuthoritiesParam();
        param.setMenuIds(menuIds);
        param.setAuthority(authority);
        param.setAuthorityType(ascription);
        param.setSize(1L);
        param.setCurrent(1L);
        param.setMethod(method);
        Response<PageResult<SysAuthoritiesDTO>> resultResponse = iSysAuthoritiesApi.queryByCondition(param);
        if (!resultResponse.isSuccess()) {
            log.error("iSysAuthoritiesApi queryByCondition failed，SysAuthoritiesQueryParam={}", JSONObject.toJSONString(param));
            throw new CustomException("查询用户权限失败");
        }
        PageResult<SysAuthoritiesDTO> pageResult = resultResponse.getData();
        return CollectionUtils.isNotEmpty(pageResult.getRecords());
    }
}
