package com.sbx.app.applet.params;

import com.sbx.core.model.params.Query;
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
public class WebsiteAppEmpowerParam extends Query {
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
