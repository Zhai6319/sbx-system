package com.sbx.app.system.api;

import com.sbx.app.system.dto.SysAuthoritiesDTO;
import com.sbx.app.system.params.SaveMenuAuthorityParam;
import com.sbx.app.system.params.SysAuthoritiesParam;
import com.sbx.core.model.api.Response;
import com.sbx.core.model.base.result.PageResult;


/**
 * <p>
 * 系统权限 dubbo接口
 * </p>
 *
 * @author Z.jc
 * @since 2021-06-26
 */
public interface ISysAuthoritiesApi {

    /**
     * 条件分页查询系统权限
     * @param param 查询条件
     * @return      返回系统权限分页列表
     */
    Response<PageResult<SysAuthoritiesDTO>> queryByCondition(SysAuthoritiesParam param);

    /**
     * 根据id查询系统权限
     * @param id 数据id
     * @return   返回系统权限
     */
    Response<SysAuthoritiesDTO> queryDetailById(Long id);

    /**
     * 创建系统权限
     * @param sysAuthoritiesDTO   系统权限数据
     * @return              返回创建数据id
     */
    Response<Long> create(SysAuthoritiesDTO sysAuthoritiesDTO);

    /**
     * 批量创建权限
     * @param param    权限列表
     * @return                      返回创建结果
     */
    Response<Boolean> batchCreate(SaveMenuAuthorityParam param);

    /**
     * 修改系统权限
     * @param sysAuthoritiesDTO   系统权限数据
     * @return              返回结果
     */
    Response<Boolean> update(SysAuthoritiesDTO sysAuthoritiesDTO);

    /**
     * 根据id删除系统权限
     * @param id    数据id
     * @return      返回删除结果
     */
    Response<Boolean> delById(Long id);

}
