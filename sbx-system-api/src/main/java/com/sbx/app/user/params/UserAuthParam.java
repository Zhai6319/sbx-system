package com.sbx.app.user.params;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>说明：</p>
 *
 * @author Z.jc
 * @version 1.0.0
 * @since 2021/6/26
 */
@Data
public class UserAuthParam implements Serializable {

    /**
     * 用户名
     */
    private String username;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 邮箱
     */
    private String email;

    /**
     * openid 或 ali user id
     */
    private String secretKey;

    /**
     * 登录类型{@link com.sbx.app.user.enums.LoginTypeEnum}
     */
    private Integer loginType;

    /**
     * 用户类型{@link com.sbx.app.user.enums.UserTypeEnum}
     */
    private Integer userType;

    /**
     * 登录方式{@link com.sbx.app.user.enums.LoginMethodEnum}
     */
    private Integer loginMethod;

    /**
     * 授权码
     */
    private String code;

    /**
     * 包括敏感数据在内的完整用户信息的加密数据
     */
    private String encryptedData;

    /**
     * 加密算法的初始向量
     */
    private String iv;


}
