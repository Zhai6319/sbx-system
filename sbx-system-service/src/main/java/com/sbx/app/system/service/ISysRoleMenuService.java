package com.sbx.app.system.service;

import com.sbx.app.system.dto.SysRoleMenuDTO;
import com.sbx.app.system.params.SysRoleMenuParam;
import com.sbx.core.model.base.result.PageResult;


/**
 * <p>
 * 角色菜单表 服务类
 * </p>
 *
 * @author Z.jc
 * @since 2021-06-26
 */
public interface ISysRoleMenuService {

    /**
     * 分页查询角色菜单表
     * @param param 查询条件
     * @return      返回分页列表
     */
    PageResult<SysRoleMenuDTO> page(SysRoleMenuParam param);

   /**
    * 根据id获取角色菜单表详情
    * @param id    数据id
    * @return      返回角色菜单表详情
    */
   SysRoleMenuDTO detailById(Long id);

    /**
     * 创建角色菜单表
     * @param sysRoleMenuDTO   角色菜单表数据
     * @return              返回创建数据id
     */
    Long create(SysRoleMenuDTO sysRoleMenuDTO);

    /**
     * 修改角色菜单表
     * @param sysRoleMenuDTO   角色菜单表数据
     */
    void update(SysRoleMenuDTO sysRoleMenuDTO);

    /**
     * 根据id删除角色菜单表
     * @param id    数据id
     * @return      返回删除结果
     */
    Boolean delById(Long id);

}
