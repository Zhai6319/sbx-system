package com.sbx.app.order.dataobject;

import com.baomidou.mybatisplus.annotation.*;
import com.sbx.core.mybatis.base.dataobject.BaseDO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
* <p>
* 订单信息
* </p>
*
* @author Z.jc
* @since 2022-06-30
*/
@Data
@Accessors(chain = true)
@TableName("order_info")
public class OrderInfo extends BaseDO {

    private static final long serialVersionUID = 1L;



    /**
     * 订单编号
     */
    private String orderSn;



}
