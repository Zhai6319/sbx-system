package com.sbx.app.user.service;

import com.sbx.app.user.dto.UserInfoDTO;
import com.sbx.app.user.dto.UserInfoDetailDTO;
import com.sbx.app.user.dto.UserNavigationBarDTO;
import com.sbx.app.user.params.UserInfoParam;
import com.sbx.app.user.params.UserSaveParam;
import com.sbx.core.model.base.result.PageResult;

import java.util.List;


/**
 * <p>
 * 用户信息 服务类
 * </p>
 *
 * @author Z.jc
 * @since 2021-06-26
 */
public interface IUserInfoService {

    /**
     * 分页查询用户信息
     * @param param 查询条件
     * @return      返回分页列表
     */
    PageResult<UserInfoDetailDTO> page(UserInfoParam param);

    /**
     * 条件分页查询
     * @param param 请求参数
     * @return  返回分页列表
     */
    PageResult<UserInfoDTO> queryByCondition(UserInfoParam param);
   /**
    * 根据id获取用户信息详情
    * @param id    数据id
    * @return      返回用户信息详情
    */
   UserInfoDTO detailById(Long id);

    /**
     * 创建用户信息
     * @param param   用户信息数据
     * @return              返回创建数据id
     */
    Long create(UserSaveParam param);

    /**
     * 修改用户信息
     * @param param   用户信息数据
     */
    void update(UserSaveParam param);

    /**
     * 根据id删除用户信息
     * @param id    数据id
     * @return      返回删除结果
     */
    Boolean delById(Long id);

    /**
     * 获取用户导航栏列表
     * @param param 用户请求参数，userid
     * @return  返回结果
     */
    List<UserNavigationBarDTO> userNavigationBarList(UserInfoParam param);

    /**
     * 根据用户id和路由获取路由关系链
     * @param userId    用户id
     * @param path  前端路由
     * @return  返回路由导航关系
     */
    List<UserNavigationBarDTO> userRouter(Long userId, String path);

    /**
     * 获取当前路由信息
     * @param userId    用户id
     * @param path  前端路由
     * @return  返回详情
     */
    UserNavigationBarDTO router(Long userId,String path);

    /**
     * 根据token获取登录用户信息
     * @param token token令牌
     * @return  返回用户信息
     */
    UserInfoDTO userByToken(String token);

    /**
     * 检查权限
     * @param authority 权限内容
     * @param method    方法
     * @param userId    用户id
     * @return  返回结果
     */
    Boolean checkUserAuthority(String authority,String method,Long userId);

}
