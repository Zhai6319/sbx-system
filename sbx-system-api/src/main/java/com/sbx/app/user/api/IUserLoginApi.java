package com.sbx.app.user.api;

import com.sbx.app.user.dto.UserAuthDTO;
import com.sbx.app.user.dto.UserLoginDTO;
import com.sbx.app.user.params.UserAuthParam;
import com.sbx.app.user.params.UserLoginParam;
import com.sbx.core.model.api.Response;
import com.sbx.core.model.base.result.PageResult;


/**
 * <p>
 * 用户登录 dubbo接口
 * </p>
 *
 * @author Z.jc
 * @since 2021-06-26
 */
public interface IUserLoginApi {

    /**
     * 条件分页查询用户登录
     * @param param 查询条件
     * @return      返回用户登录分页列表
     */
    Response<PageResult<UserLoginDTO>> queryByCondition(UserLoginParam param);

    /**
     * 根据id查询用户登录
     * @param id 数据id
     * @return   返回用户登录
     */
    Response<UserLoginDTO> queryDetailById(Long id);

    /**
     * 创建用户登录
     * @param userLoginDTO   用户登录数据
     * @return              返回创建数据id
     */
    Response<Long> create(UserLoginDTO userLoginDTO);

    /**
     * 修改用户登录
     * @param userLoginDTO   用户登录数据
     * @return              返回结果
     */
    Response<Boolean> update(UserLoginDTO userLoginDTO);

    /**
     * 根据id删除用户登录
     * @param id    数据id
     * @return      返回删除结果
     */
    Response<Boolean> delById(Long id);

    /**
     * 用户授权
     * @param param 请求参数
     * @return  返回授权信息
     */
    Response<UserAuthDTO> userAuth(UserAuthParam param);

}
