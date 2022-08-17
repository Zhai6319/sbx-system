package com.sbx.app.user.service;

import com.sbx.app.user.dto.UserAuthDTO;
import com.sbx.app.user.dto.UserLoginDTO;
import com.sbx.app.user.params.UserAuthParam;
import com.sbx.app.user.params.UserLoginParam;
import com.sbx.core.model.base.result.PageResult;


/**
 * <p>
 * 用户登录 服务类
 * </p>
 *
 * @author Z.jc
 * @since 2021-06-26
 */
public interface IUserLoginService {

    /**
     * 分页查询用户登录
     * @param param 查询条件
     * @return      返回分页列表
     */
    PageResult<UserLoginDTO> page(UserLoginParam param);

   /**
    * 根据id获取用户登录详情
    * @param id    数据id
    * @return      返回用户登录详情
    */
   UserLoginDTO detailById(Long id);

    /**
     * 创建用户登录
     * @param userLoginDTO   用户登录数据
     * @return              返回创建数据id
     */
    Long create(UserLoginDTO userLoginDTO);

    /**
     * 修改用户登录
     * @param userLoginDTO   用户登录数据
     */
    void update(UserLoginDTO userLoginDTO);

    /**
     * 根据id删除用户登录
     * @param id    数据id
     * @return      返回删除结果
     */
    Boolean delById(Long id);

    /**
     * 用户授权
     * @param param 授权请求参数
     * @return  返回用户授权信息
     */
    UserAuthDTO userAuth(UserAuthParam param);

}
