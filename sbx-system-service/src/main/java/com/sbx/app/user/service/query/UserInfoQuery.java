package com.sbx.app.user.service.query;

import com.sbx.core.model.params.Query;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.List;

/**
* <p>
* 用户信息
* </p>
*
* @author Z.jc
* @since 2021-06-26
*/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class UserInfoQuery extends Query {
    private static final long serialVersionUID = 1L;

    /**
     * 用户ids
     */
    private List<Long> ids;
    /**
     * 用户名称
     */
    private String username;
    /**
     * 昵称
     */
    private String nickname;
    /**
     * 性别 0保密 1男 2女
     */
    private Integer gender;
    /**
     * 手机号 加密存储
     */
    private String mobile;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 生日
     */
    private String birthday;
    /**
     * 头像
     */
    private String portrait;
    /**
     * 用户类型 1管理员 2c端用户
     */
    private Integer userType;
    /**
     * 是否为超级管理员 0否 1是
     */
    private Boolean superAdmin;
    /**
     * 启用状态
     */
    private Boolean enableFlag;
    /**
     * 来源 0pc 1微信小程序 2支付宝小程序 3ios 4安卓
     */
    private Integer source;


}
