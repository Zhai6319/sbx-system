package com.sbx.app.enterprise.dataobject;

import com.baomidou.mybatisplus.annotation.TableName;
import com.sbx.core.mybatis.base.dataobject.BaseDO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
* <p>
* 企业信息
* </p>
*
* @author Z.jc
* @since 2022-10-02
*/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("enterprise")
public class Enterprise extends BaseDO {

    private static final long serialVersionUID = 1L;

    /**
     * 企业名称
     */
    private String enterpriseName;

    /**
     * 企业logo
     */
    private String enterpriseLogo;

    /**
     * 联系人
     */
    private String linkMan;

    /**
     * 联系电话
     */
    private String linkMobile;

    /**
     * 备注
     */
    private String remarks;


}
