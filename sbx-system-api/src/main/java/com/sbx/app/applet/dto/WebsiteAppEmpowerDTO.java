package com.sbx.app.applet.dto;

import com.sbx.core.model.base.dto.BaseDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
* <p>
* 域名应用授权
* </p>
*
* @author Z.jc
* @since 2022-05-20
*/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class WebsiteAppEmpowerDTO extends BaseDTO {
    private static final long serialVersionUID = 1L;
    /**
    * 域名
    */
    private String domainName;
    /**
    * 授权应用
    */
    private String appId;


}
