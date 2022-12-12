package com.sbx.app.user.service.executor.user.update.model;

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
public class UpdateUserModel implements Serializable {
    private static final long serialVersionUID = -6356106743364288567L;

    /**
     * 用户信息
     */
    private UserInfoDTO userInfo;

    /**
     * 角色id列表
     */
    private List<Long> roleIds;
}
