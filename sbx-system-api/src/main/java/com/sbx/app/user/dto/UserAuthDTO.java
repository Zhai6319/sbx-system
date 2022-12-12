package com.sbx.app.user.dto;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>说明：</p>
 *
 * @author Z.jc
 * @version 1.0.0
 * @since 2021/6/26
 */
@Data
public class UserAuthDTO implements Serializable {
    /**
     * 用户id
     */
    private Long userId;
    /**
     * 用户类型
     */
    private Integer userType;
    private Integer clientType;
    private String token;
    private LocalDateTime tokenTime;
    private Long expiredSeconds;
}
