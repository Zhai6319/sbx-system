package com.sbx.app.user.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * <p>说明：</p>
 *
 * @author Z.jc
 * @version 1.0.0
 * @since 2021/1/6
 */
@Data
public class UserButtonDTO implements Serializable {
    private static final long serialVersionUID = -621644322368251961L;

    private String title;

    private String name;

    private String path;

    private String component;

    private String icon;
}
