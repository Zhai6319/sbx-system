package com.sbx.app.system.api;

import com.sbx.app.system.dto.SysRoleMenuDTO;
import com.sbx.app.system.params.SysRoleMenuParam;
import com.sbx.core.model.api.Response;
import com.sbx.core.model.base.result.PageResult;


/**
 * <p>
 * 角色菜单表 dubbo接口
 * </p>
 *
 * @author Z.jc
 * @since 2021-06-26
 */
public interface ISysRoleMenuApi {

    /**
     * 条件分页查询角色菜单表
     * @param param 查询条件
     * @return      返回角色菜单表分页列表
     */
    Response<PageResult<SysRoleMenuDTO>> queryByCondition(SysRoleMenuParam param);

    /**
     * 根据id查询角色菜单表
     * @param id 数据id
     * @return   返回角色菜单表
     */
    Response<SysRoleMenuDTO> queryDetailById(Long id);

    /**
     * 创建角色菜单表
     * @param sysRoleMenuDTO   角色菜单表数据
     * @return              返回创建数据id
     */
    Response<Long> create(SysRoleMenuDTO sysRoleMenuDTO);

    /**
     * 修改角色菜单表
     * @param sysRoleMenuDTO   角色菜单表数据
     * @return              返回结果
     */
    Response<Boolean> update(SysRoleMenuDTO sysRoleMenuDTO);

    /**
     * 根据id删除角色菜单表
     * @param id    数据id
     * @return      返回删除结果
     */
    Response<Boolean> delById(Long id);

}
