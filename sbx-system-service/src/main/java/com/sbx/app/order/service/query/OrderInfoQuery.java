package com.sbx.app.order.service.query;

import com.sbx.core.model.params.Query;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
* <p>
* 订单信息
* </p>
*
* @author Z.jc
* @since 2022-06-30
*/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class OrderInfoQuery extends Query {
    private static final long serialVersionUID = 1L;
    /**
     * 订单编号
     */
    private String orderSn;


}
