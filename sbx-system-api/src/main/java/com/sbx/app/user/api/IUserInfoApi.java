package com.sbx.app.user.api;

import com.sbx.app.user.dto.UserInfoDTO;
import com.sbx.app.user.dto.UserInfoDetailDTO;
import com.sbx.app.user.dto.UserNavigationBarDTO;
import com.sbx.app.user.params.UserInfoParam;
import com.sbx.app.user.params.UserSaveParam;
import com.sbx.core.model.api.Response;
import com.sbx.core.model.base.result.PageResult;

import java.util.List;


/**
 * <p>
 * 用户信息 dubbo接口
 * </p>
 *
 * @author Z.jc
 * @since 2021-06-26
 */
public interface IUserInfoApi {

    /**
     * 条件分页查询用户信息
     * @param param 查询条件
     * @return      返回用户信息分页列表
     */
    Response<PageResult<UserInfoDetailDTO>> queryByCondition(UserInfoParam param);

    /**
     * 条件分页查询用户信息
     * @param param 查询条件
     * @return  返回分页数据
     */
    Response<PageResult<UserInfoDTO>> queryPageByCondition(UserInfoParam param);
    /**
     * 根据id查询用户信息
     * @param id 数据id
     * @return   返回用户信息
     */
    Response<UserInfoDTO> queryDetailById(Long id);

    /**
     * 创建用户信息
     * @param param   用户信息数据
     * @return              返回创建数据id
     */
    Response<Long> create(UserSaveParam param);

    /**
     * 修改用户信息
     * @param param   用户信息数据
     * @return              返回结果
     */
    Response<Boolean> update(UserSaveParam param);

    /**
     * 根据id删除用户信息
     * @param id    数据id
     * @return      返回删除结果
     */
    Response<Boolean> delById(Long id);

    /**
     * 获取用户导航栏
     * @param param 请求参数 userId
     * @return 返回导航栏列表
     */
    Response<List<UserNavigationBarDTO>> userNavigationBarList(UserInfoParam param);

    /**
     * 根据用户id和路由获取路由关系链
     * @param userId    用户id
     * @param path  前端路由
     * @return  返回路由导航关系
     */
    Response<List<UserNavigationBarDTO>> userRouter(Long userId, String path);

    /**
     * 获取当前路由
     * @param userId    用户id
     * @param path  前端路由
     * @return  返回路由数据
     */
    Response<UserNavigationBarDTO> router(Long userId, String path);

    /**
     * 根据token获取用户信息
     * @param token 用户token
     * @return  返回结果
     */
    Response<UserInfoDTO> userByToken(String token);

    /**
     * 检查权限
     * @param authority 权限内容
     * @param method    方法
     * @param userId    用户id
     * @return  返回结果
     */
    Response<Boolean> checkUserAuthority(String authority,String method,Long userId);

}
