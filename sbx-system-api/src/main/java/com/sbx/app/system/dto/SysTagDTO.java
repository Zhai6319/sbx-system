package com.sbx.app.system.dto;

import com.sbx.core.model.base.dto.BaseDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
* <p>
* 系统标签
* </p>
*
* @author Z.jc
* @since 2021-07-16
*/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class SysTagDTO extends BaseDTO {
    private static final long serialVersionUID = 1L;
    /**
    * 标签名称
    */
    private String tagName;
    /**
    * 标签类型 1用户标签 2产品标签
    */
    private Integer tagType;
    /**
    * 启用标识
    */
    private Boolean enableFlag;


}
