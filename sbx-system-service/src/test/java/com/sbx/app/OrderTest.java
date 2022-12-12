package com.sbx.app;

import com.alibaba.fastjson.JSONObject;
import com.sbx.app.order.dto.OrderInfoDTO;
import com.sbx.app.order.params.OrderInfoParam;
import com.sbx.app.order.service.IOrderInfoService;
import com.sbx.core.model.base.result.PageResult;
import com.sbx.core.test.SbxBootTest;
import com.sbx.core.test.SbxSpringRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 说明：
 * </p>
 *
 * @author Z.jc
 * @since 2022/6/30
 */
@SbxBootTest(appName = "sbx-system",enableLoader = true)
@RunWith(SbxSpringRunner.class)
public class OrderTest {


    @Resource
    private IOrderInfoService iOrderInfoService;

    @Test
    public void orderTest(){
        for (int i = 0; i < 100; i++) {
            OrderInfoDTO orderInfoDTO = new OrderInfoDTO();
            orderInfoDTO.setOrderSn("234afae2e54");
            Long aLong = iOrderInfoService.create(orderInfoDTO);
        }




    }

    @Test
    public void getOrderTest(){
        OrderInfoParam param = new OrderInfoParam();
        PageResult<OrderInfoDTO> page = iOrderInfoService.page(param);
        System.out.println(JSONObject.toJSONString(page));
    }

}
