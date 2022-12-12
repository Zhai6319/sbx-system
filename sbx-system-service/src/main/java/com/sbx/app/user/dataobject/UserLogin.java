package com.sbx.app.user.dataobject;

import com.baomidou.mybatisplus.annotation.TableName;
import com.sbx.core.mybatis.base.dataobject.BaseDO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
* <p>
* 用户登录
* </p>
*
* @author Z.jc
* @since 2021-06-26
*/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("user_login")
public class UserLogin extends BaseDO {

    private static final long serialVersionUID = 1L;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 登陆类型 0密码 1微信openid 2支付宝userid
     */
    private Integer loginType;

    /**
     * 微信联合id
     */
    private String unionId;

    /**
     * 密钥
     */
    private String secretKey;


}
