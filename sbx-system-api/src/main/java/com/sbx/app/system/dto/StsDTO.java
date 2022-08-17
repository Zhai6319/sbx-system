package com.sbx.app.system.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * <p>说明：</p>
 *
 * @author Z.jc
 * @version 1.0.0
 * @since 2021/1/7
 */
@Data
public class StsDTO implements Serializable {
    private static final long serialVersionUID = 7225663499077677678L;

    private String securityToken;
    private String accessKeySecret;
    private String accessKeyId;
    private String expiration;
    private String region;
    private String bucket;
    private String signature;
    private String policyText;
    private String objectName;

}
