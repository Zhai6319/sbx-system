package com.sbx.app.applet.dataobject;

import com.baomidou.mybatisplus.annotation.TableName;
import com.sbx.core.mybatis.base.dataobject.BaseDO;
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
@TableName("website_app_empower")
public class WebsiteAppEmpower extends BaseDO {

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
