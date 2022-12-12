package com.sbx.app.user.api.impl;

import com.sbx.app.user.api.IUserLoginApi;
import com.sbx.app.user.dto.UserAuthDTO;
import com.sbx.app.user.dto.UserLoginDTO;
import com.sbx.app.user.params.UserAuthParam;
import com.sbx.app.user.params.UserLoginParam;
import com.sbx.app.user.service.IUserLoginService;
import com.sbx.core.model.api.Response;
import com.sbx.core.model.base.result.PageResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;

import javax.annotation.Resource;

/**
 * <p>
 * 用户登录 dubbo接口实现
 * </p>
 *
 * @author Z.jc
 * @since 2021-06-26
 */
@Slf4j
@DubboService
public class UserLoginApiImpl implements IUserLoginApi {

    @Resource
    private IUserLoginService iUserLoginService;

    @Override
    public Response<PageResult<UserLoginDTO>> queryByCondition(UserLoginParam param) {
        return Response.data(iUserLoginService.page(param));
    }

    @Override
    public Response<UserLoginDTO> queryDetailById(Long id) {
        return Response.data(iUserLoginService.detailById(id));
    }

    @Override
    public Response<Long> create(UserLoginDTO userLoginDTO) {
        return Response.data(iUserLoginService.create(userLoginDTO));
    }

    @Override
    public Response<Boolean> update(UserLoginDTO userLoginDTO) {
        iUserLoginService.update(userLoginDTO);
        return Response.success("修改数据成功");
    }

    @Override
    public Response<Boolean> delById(Long id) {
        return Response.data(iUserLoginService.delById(id));
    }

    @Override
    public Response<UserAuthDTO> userAuth(UserAuthParam param) {
        return Response.data(iUserLoginService.userAuth(param));
    }

}
