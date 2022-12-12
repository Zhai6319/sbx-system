package com.sbx.app.system.api;

import com.sbx.app.system.dto.SysTagDTO;
import com.sbx.app.system.params.SysTagParam;
import com.sbx.core.model.api.Response;
import com.sbx.core.model.base.result.PageResult;


/**
 * <p>
 * 系统标签 dubbo接口
 * </p>
 *
 * @author Z.jc
 * @since 2021-07-16
 */
public interface ISysTagApi {

    /**
     * 条件分页查询系统标签
     * @param param 查询条件
     * @return      返回系统标签分页列表
     */
    Response<PageResult<SysTagDTO>> queryByCondition(SysTagParam param);

    /**
     * 根据id查询系统标签
     * @param id 数据id
     * @return   返回系统标签
     */
    Response<SysTagDTO> queryDetailById(Long id);

    /**
     * 创建系统标签
     * @param sysTagDTO   系统标签数据
     * @return              返回创建数据id
     */
    Response<Long> create(SysTagDTO sysTagDTO);

    /**
     * 修改系统标签
     * @param sysTagDTO   系统标签数据
     * @return              返回结果
     */
    Response<Boolean> update(SysTagDTO sysTagDTO);

    /**
     * 根据id删除系统标签
     * @param id    数据id
     * @return      返回删除结果
     */
    Response<Boolean> delById(Long id);

}
