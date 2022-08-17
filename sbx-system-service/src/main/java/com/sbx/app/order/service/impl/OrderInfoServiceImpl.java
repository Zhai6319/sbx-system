package com.sbx.app.order.service.impl;

import com.sbx.app.order.dto.OrderInfoDTO;
import com.sbx.app.order.params.OrderInfoParam;
import com.sbx.app.order.service.IOrderInfoService;
import com.sbx.app.order.service.deal.OrderInfoDeal;
import com.sbx.app.order.service.query.OrderInfoQuery;
import com.sbx.core.model.base.result.PageResult;
import com.sbx.core.tool.util.ObjectUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * 订单信息 服务实现类
 * </p>
 *
 * @author Z.jc
 * @since 2022-06-30
 */
@Slf4j
@Service
public class OrderInfoServiceImpl implements IOrderInfoService {

    @Resource
    private OrderInfoDeal orderInfoDeal;

    @Override
    public PageResult<OrderInfoDTO> page(OrderInfoParam param) {
        OrderInfoQuery query = ObjectUtils.copy(param,OrderInfoQuery.class);
        return orderInfoDeal.queryByCondition(query);
    }

    @Override
    public OrderInfoDTO detailById(Long id) {
        return orderInfoDeal.queryById(id);
    }

    @Override
    public Long create(OrderInfoDTO orderInfoDTO) {
        return orderInfoDeal.create(orderInfoDTO);
    }

    @Override
    public void update(OrderInfoDTO orderInfoDTO) {
        orderInfoDeal.update(orderInfoDTO);
    }

    @Override
    public Boolean delById(Long id) {
        return orderInfoDeal.delById(id);
    }

}
