package com.sbx.app.user.api;

import com.sbx.app.user.dto.UserRoleDTO;
import com.sbx.app.user.params.UserRoleParam;
import com.sbx.core.model.api.Response;
import com.sbx.core.model.base.result.PageResult;


/**
 * <p>
 * 用户角色关联表 dubbo接口
 * </p>
 *
 * @author Z.jc
 * @since 2021-06-26
 */
public interface IUserRoleApi {

    /**
     * 条件分页查询用户角色关联表
     * @param param 查询条件
     * @return      返回用户角色关联表分页列表
     */
    Response<PageResult<UserRoleDTO>> queryByCondition(UserRoleParam param);

    /**
     * 根据id查询用户角色关联表
     * @param id 数据id
     * @return   返回用户角色关联表
     */
    Response<UserRoleDTO> queryDetailById(Long id);

    /**
     * 创建用户角色关联表
     * @param userRoleDTO   用户角色关联表数据
     * @return              返回创建数据id
     */
    Response<Long> create(UserRoleDTO userRoleDTO);

    /**
     * 修改用户角色关联表
     * @param userRoleDTO   用户角色关联表数据
     * @return              返回结果
     */
    Response<Boolean> update(UserRoleDTO userRoleDTO);

    /**
     * 根据id删除用户角色关联表
     * @param id    数据id
     * @return      返回删除结果
     */
    Response<Boolean> delById(Long id);

}
