package com.sbx.app.order.dto;

import com.sbx.core.model.base.dto.BaseDTO;
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
public class OrderInfoDTO extends BaseDTO {
    private static final long serialVersionUID = 1L;
    /**
    * 订单编号
    */
    private String orderSn;


}
