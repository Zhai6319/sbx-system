package com.sbx.app.user.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * <p>说明：</p>
 *
 * @author Z.jc
 * @version 1.0.0
 * @since 2021/7/9
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class UserInfoDetailDTO extends UserInfoDTO {
    private static final long serialVersionUID = -6186270104272746917L;

    private List<Long> roleIds;

}
