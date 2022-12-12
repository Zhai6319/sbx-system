package com.sbx.app.order.api;

import com.sbx.app.order.dto.OrderInfoDTO;
import com.sbx.app.order.params.OrderInfoParam;
import com.sbx.core.model.api.Response;
import com.sbx.core.model.base.result.PageResult;


/**
 * <p>
 * 订单信息 dubbo接口
 * </p>
 *
 * @author Z.jc
 * @since 2022-06-30
 */
public interface IOrderInfoApi {

    /**
     * 条件分页查询订单信息
     * @param param 查询条件
     * @return      返回订单信息分页列表
     */
    Response<PageResult<OrderInfoDTO>> queryByCondition(OrderInfoParam param);

    /**
     * 根据id查询订单信息
     * @param id 数据id
     * @return   返回订单信息
     */
    Response<OrderInfoDTO> queryDetailById(Long id);

    /**
     * 创建订单信息
     * @param orderInfoDTO   订单信息数据
     * @return              返回创建数据id
     */
    Response<Long> create(OrderInfoDTO orderInfoDTO);

    /**
     * 修改订单信息
     * @param orderInfoDTO   订单信息数据
     * @return              返回结果
     */
    Response<Boolean> update(OrderInfoDTO orderInfoDTO);

    /**
     * 根据id删除订单信息
     * @param id    数据id
     * @return      返回删除结果
     */
    Response<Boolean> delById(Long id);

}
