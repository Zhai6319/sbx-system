package com.sbx.app.system.service;

import com.sbx.app.system.dto.SysTagDTO;
import com.sbx.app.system.params.SysTagParam;
import com.sbx.core.model.base.result.PageResult;


/**
 * <p>
 * 系统标签 服务类
 * </p>
 *
 * @author Z.jc
 * @since 2021-07-16
 */
public interface ISysTagService {

    /**
     * 分页查询系统标签
     * @param param 查询条件
     * @return      返回分页列表
     */
    PageResult<SysTagDTO> page(SysTagParam param);

   /**
    * 根据id获取系统标签详情
    * @param id    数据id
    * @return      返回系统标签详情
    */
   SysTagDTO detailById(Long id);

    /**
     * 创建系统标签
     * @param sysTagDTO   系统标签数据
     * @return              返回创建数据id
     */
    Long create(SysTagDTO sysTagDTO);

    /**
     * 修改系统标签
     * @param sysTagDTO   系统标签数据
     */
    void update(SysTagDTO sysTagDTO);

    /**
     * 根据id删除系统标签
     * @param id    数据id
     * @return      返回删除结果
     */
    Boolean delById(Long id);

}
