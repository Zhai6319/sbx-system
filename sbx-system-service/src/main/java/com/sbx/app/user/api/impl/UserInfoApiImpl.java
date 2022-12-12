package com.sbx.app.user.api.impl;

import com.sbx.app.user.api.IUserInfoApi;
import com.sbx.app.user.dto.UserInfoDTO;
import com.sbx.app.user.dto.UserInfoDetailDTO;
import com.sbx.app.user.dto.UserNavigationBarDTO;
import com.sbx.app.user.params.UserInfoParam;
import com.sbx.app.user.params.UserSaveParam;
import com.sbx.app.user.service.IUserInfoService;
import com.sbx.core.model.api.Response;
import com.sbx.core.model.base.result.PageResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 用户信息 dubbo接口实现
 * </p>
 *
 * @author Z.jc
 * @since 2021-06-26
 */
@Slf4j
@DubboService
public class UserInfoApiImpl implements IUserInfoApi {

    @Resource
    private IUserInfoService iUserInfoService;

    @Override
    public Response<PageResult<UserInfoDetailDTO>> queryByCondition(UserInfoParam param) {
        return Response.data(iUserInfoService.page(param));
    }

    @Override
    public Response<PageResult<UserInfoDTO>> queryPageByCondition(UserInfoParam param) {
        return Response.data(iUserInfoService.queryByCondition(param));
    }

    @Override
    public Response<UserInfoDTO> queryDetailById(Long id) {
        return Response.data(iUserInfoService.detailById(id));
    }

    @Override
    public Response<Long> create(UserSaveParam param) {
        return Response.data(iUserInfoService.create(param));
    }

    @Override
    public Response<Boolean> update(UserSaveParam param) {
        iUserInfoService.update(param);
        return Response.success("修改数据成功");
    }

    @Override
    public Response<Boolean> delById(Long id) {
        return Response.data(iUserInfoService.delById(id));
    }

    @Override
    public Response<List<UserNavigationBarDTO>> userNavigationBarList(UserInfoParam param) {
        return Response.data(iUserInfoService.userNavigationBarList(param));
    }

    @Override
    public Response<List<UserNavigationBarDTO>> userRouter(Long userId, String path) {
        return Response.data(iUserInfoService.userRouter(userId,path));
    }

    @Override
    public Response<UserNavigationBarDTO> router(Long userId, String path) {
        return Response.data(iUserInfoService.router(userId,path));
    }

    @Override
    public Response<UserInfoDTO> userByToken(String token) {
        return Response.data(iUserInfoService.userByToken(token));
    }

    @Override
    public Response<Boolean> checkUserAuthority(String authority, String method, Long userId) {
        return Response.data(iUserInfoService.checkUserAuthority(authority,method,userId));
    }

}
