package com.sbx.app.user.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * <p>说明：</p>
 *
 * @author Z.jc
 * @version 1.0.0
 * @since 2020/12/29
 */
@Data
public class UserNavigationMetaDTO implements Serializable {
    private static final long serialVersionUID = -6563085226828086505L;

    private String icon;

    private String title;

    private Boolean show;

    private List<UserButtonDTO> buttons;

}
