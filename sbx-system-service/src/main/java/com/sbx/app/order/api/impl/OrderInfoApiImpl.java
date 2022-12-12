package com.sbx.app.order.api.impl;

import com.sbx.app.order.api.IOrderInfoApi;
import com.sbx.app.order.dto.OrderInfoDTO;
import com.sbx.app.order.params.OrderInfoParam;
import com.sbx.app.order.service.IOrderInfoService;
import com.sbx.core.model.api.Response;
import com.sbx.core.model.base.result.PageResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;

import javax.annotation.Resource;

/**
 * <p>
 * 订单信息 dubbo接口实现
 * </p>
 *
 * @author Z.jc
 * @since 2022-06-30
 */
@Slf4j
@DubboService
public class OrderInfoApiImpl implements IOrderInfoApi {

    @Resource
    private IOrderInfoService iOrderInfoService;

    @Override
    public Response<PageResult<OrderInfoDTO>> queryByCondition(OrderInfoParam param) {
        return Response.data(iOrderInfoService.page(param));
    }

    @Override
    public Response<OrderInfoDTO> queryDetailById(Long id) {
        return Response.data(iOrderInfoService.detailById(id));
    }

    @Override
    public Response<Long> create(OrderInfoDTO orderInfoDTO) {
        return Response.data(iOrderInfoService.create(orderInfoDTO));
    }

    @Override
    public Response<Boolean> update(OrderInfoDTO orderInfoDTO) {
        iOrderInfoService.update(orderInfoDTO);
        return Response.success("修改数据成功");
    }

    @Override
    public Response<Boolean> delById(Long id) {
        return Response.data(iOrderInfoService.delById(id));
    }

}
