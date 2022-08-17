package com.sbx.app.user.api.impl;

import com.sbx.app.user.api.IUserRoleApi;
import com.sbx.app.user.dto.UserRoleDTO;
import com.sbx.app.user.params.UserRoleParam;
import com.sbx.app.user.service.IUserRoleService;
import com.sbx.core.model.api.Response;
import com.sbx.core.model.base.result.PageResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;

import javax.annotation.Resource;

/**
 * <p>
 * 用户角色关联表 dubbo接口实现
 * </p>
 *
 * @author Z.jc
 * @since 2021-06-26
 */
@Slf4j
@DubboService
public class UserRoleApiImpl implements IUserRoleApi {

    @Resource
    private IUserRoleService iUserRoleService;

    @Override
    public Response<PageResult<UserRoleDTO>> queryByCondition(UserRoleParam param) {
        return Response.data(iUserRoleService.page(param));
    }

    @Override
    public Response<UserRoleDTO> queryDetailById(Long id) {
        return Response.data(iUserRoleService.detailById(id));
    }

    @Override
    public Response<Long> create(UserRoleDTO userRoleDTO) {
        return Response.data(iUserRoleService.create(userRoleDTO));
    }

    @Override
    public Response<Boolean> update(UserRoleDTO userRoleDTO) {
        iUserRoleService.update(userRoleDTO);
        return Response.success("修改数据成功");
    }

    @Override
    public Response<Boolean> delById(Long id) {
        return Response.data(iUserRoleService.delById(id));
    }

}
