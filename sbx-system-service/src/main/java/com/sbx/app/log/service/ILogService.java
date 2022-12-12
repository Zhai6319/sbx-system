package com.sbx.app.log.service;

import com.sbx.app.log.dto.LogDTO;
import com.sbx.app.log.params.LogParam;
import com.sbx.core.model.base.result.PageResult;


/**
 * <p>
 * 日志记录 服务类
 * </p>
 *
 * @author Z.jc
 * @since 2021-10-29
 */
public interface ILogService {

    /**
     * 分页查询日志记录
     * @param param 查询条件
     * @return      返回分页列表
     */
    PageResult<LogDTO> page(LogParam param);

   /**
    * 根据id获取日志记录详情
    * @param id    数据id
    * @return      返回日志记录详情
    */
   LogDTO detailById(Long id);

    /**
     * 创建日志记录
     * @param logDTO   日志记录数据
     * @return              返回创建数据id
     */
    Long create(LogDTO logDTO);

    /**
     * 修改日志记录
     * @param logDTO   日志记录数据
     */
    void update(LogDTO logDTO);

    /**
     * 根据id删除日志记录
     * @param id    数据id
     * @return      返回删除结果
     */
    Boolean delById(Long id);

}
