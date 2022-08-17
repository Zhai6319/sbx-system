package com.sbx.app.user.service.impl;

import com.sbx.app.user.dto.UserRoleDTO;
import com.sbx.app.user.params.UserRoleParam;
import com.sbx.app.user.service.IUserRoleService;
import com.sbx.app.user.service.deal.UserRoleDeal;
import com.sbx.app.user.service.query.UserRoleQuery;
import com.sbx.core.model.base.result.PageResult;
import com.sbx.core.tool.util.ObjectUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * 用户角色关联表 服务实现类
 * </p>
 *
 * @author Z.jc
 * @since 2021-06-26
 */
@Slf4j
@Service
public class UserRoleServiceImpl implements IUserRoleService {

    @Resource
    private UserRoleDeal userRoleDeal;

    @Override
    public PageResult<UserRoleDTO> page(UserRoleParam param) {
        UserRoleQuery query = ObjectUtils.copy(param,UserRoleQuery.class);
        return userRoleDeal.queryByCondition(query);
    }

    @Override
    public UserRoleDTO detailById(Long id) {
        return userRoleDeal.queryById(id);
    }

    @Override
    public Long create(UserRoleDTO userRoleDTO) {
        return userRoleDeal.create(userRoleDTO);
    }

    @Override
    public void update(UserRoleDTO userRoleDTO) {
        userRoleDeal.update(userRoleDTO);
    }

    @Override
    public Boolean delById(Long id) {
        return userRoleDeal.delById(id);
    }

}
