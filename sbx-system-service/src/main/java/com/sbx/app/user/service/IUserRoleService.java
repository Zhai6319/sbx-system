package com.sbx.app.user.service;

import com.sbx.app.user.dto.UserRoleDTO;
import com.sbx.app.user.params.UserRoleParam;
import com.sbx.core.model.base.result.PageResult;


/**
 * <p>
 * 用户角色关联表 服务类
 * </p>
 *
 * @author Z.jc
 * @since 2021-06-26
 */
public interface IUserRoleService {

    /**
     * 分页查询用户角色关联表
     * @param param 查询条件
     * @return      返回分页列表
     */
    PageResult<UserRoleDTO> page(UserRoleParam param);

   /**
    * 根据id获取用户角色关联表详情
    * @param id    数据id
    * @return      返回用户角色关联表详情
    */
   UserRoleDTO detailById(Long id);

    /**
     * 创建用户角色关联表
     * @param userRoleDTO   用户角色关联表数据
     * @return              返回创建数据id
     */
    Long create(UserRoleDTO userRoleDTO);

    /**
     * 修改用户角色关联表
     * @param userRoleDTO   用户角色关联表数据
     */
    void update(UserRoleDTO userRoleDTO);

    /**
     * 根据id删除用户角色关联表
     * @param id    数据id
     * @return      返回删除结果
     */
    Boolean delById(Long id);

}
