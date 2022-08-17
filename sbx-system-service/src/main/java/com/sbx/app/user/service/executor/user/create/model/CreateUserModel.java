package com.sbx.app.user.service.executor.user.create.model;

import com.sbx.app.user.dto.UserInfoDTO;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * <p>说明：</p>
 *
 * @author Z.jc
 * @version 1.0.0
 * @since 2021/6/27
 */
@Data
public class CreateUserModel implements Serializable {
    private static final long serialVersionUID = 6088109325213460908L;

    /**
     * 基础用户信息
     */
    private UserInfoDTO userInfo;

    /**
     * 用户密码
     */
    private String password;

    /**
     * 第三方密钥
     */
    private String secretKey;

    private Integer loginType;

    private String unionId;

    /**
     * 角色id列表
     */
    private List<Long> roleIds;
}
