package com.sbx.app.log.api;

import com.sbx.app.log.dto.LogDTO;
import com.sbx.app.log.params.LogParam;
import com.sbx.core.model.api.Response;
import com.sbx.core.model.base.result.PageResult;


/**
 * <p>
 * 日志记录 dubbo接口
 * </p>
 *
 * @author Z.jc
 * @since 2021-10-29
 */
public interface ILogApi {

    /**
     * 条件分页查询日志记录
     * @param param 查询条件
     * @return      返回日志记录分页列表
     */
    Response<PageResult<LogDTO>> queryByCondition(LogParam param);

    /**
     * 根据id查询日志记录
     * @param id 数据id
     * @return   返回日志记录
     */
    Response<LogDTO> queryDetailById(Long id);

    /**
     * 创建日志记录
     * @param logDTO   日志记录数据
     * @return              返回创建数据id
     */
    Response<Long> create(LogDTO logDTO);

    /**
     * 修改日志记录
     * @param logDTO   日志记录数据
     * @return              返回结果
     */
    Response<Boolean> update(LogDTO logDTO);

    /**
     * 根据id删除日志记录
     * @param id    数据id
     * @return      返回删除结果
     */
    Response<Boolean> delById(Long id);

}
