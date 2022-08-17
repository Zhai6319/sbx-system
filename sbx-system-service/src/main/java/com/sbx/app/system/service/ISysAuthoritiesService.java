package com.sbx.app.system.service;

import com.sbx.app.system.dto.SysAuthoritiesDTO;
import com.sbx.app.system.params.SaveMenuAuthorityParam;
import com.sbx.app.system.params.SysAuthoritiesParam;
import com.sbx.core.model.base.result.PageResult;


/**
 * <p>
 * 系统权限 服务类
 * </p>
 *
 * @author Z.jc
 * @since 2021-06-26
 */
public interface ISysAuthoritiesService {

    /**
     * 分页查询系统权限
     * @param param 查询条件
     * @return      返回分页列表
     */
    PageResult<SysAuthoritiesDTO> page(SysAuthoritiesParam param);

   /**
    * 根据id获取系统权限详情
    * @param id    数据id
    * @return      返回系统权限详情
    */
   SysAuthoritiesDTO detailById(Long id);

    /**
     * 创建系统权限
     * @param sysAuthoritiesDTO   系统权限数据
     * @return              返回创建数据id
     */
    Long create(SysAuthoritiesDTO sysAuthoritiesDTO);

    /**
     * 批量创建菜单权限
     * @param param    数据
     * @return         返回创建结果
     */
    Boolean batchCreate(SaveMenuAuthorityParam param);

    /**
     * 修改系统权限
     * @param sysAuthoritiesDTO   系统权限数据
     */
    void update(SysAuthoritiesDTO sysAuthoritiesDTO);

    /**
     * 根据id删除系统权限
     * @param id    数据id
     * @return      返回删除结果
     */
    Boolean delById(Long id);

}
