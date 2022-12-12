package com.sbx.app.system.api;

import com.sbx.app.system.dto.SysRoleDTO;
import com.sbx.app.system.params.SysRoleParam;
import com.sbx.core.model.api.Response;
import com.sbx.core.model.base.result.PageResult;


/**
 * <p>
 * 系统角色 dubbo接口
 * </p>
 *
 * @author Z.jc
 * @since 2021-06-26
 */
public interface ISysRoleApi {

    /**
     * 条件分页查询系统角色
     * @param param 查询条件
     * @return      返回系统角色分页列表
     */
    Response<PageResult<SysRoleDTO>> queryByCondition(SysRoleParam param);

    /**
     * 根据id查询系统角色
     * @param id 数据id
     * @return   返回系统角色
     */
    Response<SysRoleDTO> queryDetailById(Long id);

    /**
     * 创建系统角色
     * @param sysRoleDTO   系统角色数据
     * @return              返回创建数据id
     */
    Response<Long> create(SysRoleDTO sysRoleDTO);

    /**
     * 修改系统角色
     * @param sysRoleDTO   系统角色数据
     * @return              返回结果
     */
    Response<Boolean> update(SysRoleDTO sysRoleDTO);

    /**
     * 根据id删除系统角色
     * @param id    数据id
     * @return      返回删除结果
     */
    Response<Boolean> delById(Long id);

}
