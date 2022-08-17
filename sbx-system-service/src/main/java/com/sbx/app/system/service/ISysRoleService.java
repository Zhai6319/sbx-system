package com.sbx.app.system.service;

import com.sbx.app.system.dto.SysRoleDTO;
import com.sbx.app.system.params.SysRoleParam;
import com.sbx.core.model.base.result.PageResult;


/**
 * <p>
 * 系统角色 服务类
 * </p>
 *
 * @author Z.jc
 * @since 2021-06-26
 */
public interface ISysRoleService {

    /**
     * 分页查询系统角色
     * @param param 查询条件
     * @return      返回分页列表
     */
    PageResult<SysRoleDTO> page(SysRoleParam param);

   /**
    * 根据id获取系统角色详情
    * @param id    数据id
    * @return      返回系统角色详情
    */
   SysRoleDTO detailById(Long id);

    /**
     * 创建系统角色
     * @param sysRoleDTO   系统角色数据
     * @return              返回创建数据id
     */
    Long create(SysRoleDTO sysRoleDTO);

    /**
     * 修改系统角色
     * @param sysRoleDTO   系统角色数据
     */
    void update(SysRoleDTO sysRoleDTO);

    /**
     * 根据id删除系统角色
     * @param id    数据id
     * @return      返回删除结果
     */
    Boolean delById(Long id);

}
