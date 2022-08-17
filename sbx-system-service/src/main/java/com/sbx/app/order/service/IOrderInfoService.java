package com.sbx.app.order.service;

import com.sbx.app.order.dto.OrderInfoDTO;
import com.sbx.app.order.params.OrderInfoParam;
import com.sbx.core.model.base.result.PageResult;


/**
 * <p>
 * 订单信息 服务类
 * </p>
 *
 * @author Z.jc
 * @since 2022-06-30
 */
public interface IOrderInfoService {

    /**
     * 分页查询订单信息
     * @param param 查询条件
     * @return      返回分页列表
     */
    PageResult<OrderInfoDTO> page(OrderInfoParam param);

   /**
    * 根据id获取订单信息详情
    * @param id    数据id
    * @return      返回订单信息详情
    */
   OrderInfoDTO detailById(Long id);

    /**
     * 创建订单信息
     * @param orderInfoDTO   订单信息数据
     * @return              返回创建数据id
     */
    Long create(OrderInfoDTO orderInfoDTO);

    /**
     * 修改订单信息
     * @param orderInfoDTO   订单信息数据
     */
    void update(OrderInfoDTO orderInfoDTO);

    /**
     * 根据id删除订单信息
     * @param id    数据id
     * @return      返回删除结果
     */
    Boolean delById(Long id);

}
