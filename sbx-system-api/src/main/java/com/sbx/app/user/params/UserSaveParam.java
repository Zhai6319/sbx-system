package com.sbx.app.user.params;

import com.sbx.app.user.dto.UserInfoDTO;
import com.sbx.app.user.enums.LoginTypeEnum;
import com.sbx.core.model.enums.EResultCode;
import com.sbx.core.model.validator.Validator;
import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 * <p>说明：</p>
 *
 * @author Z.jc
 * @version 1.0.0
 * @since 2021/6/27
 */
@Data
public class UserSaveParam implements Serializable {
    private static final long serialVersionUID = -1820134492236609694L;

    /**
     * 用户信息
     */
    private UserInfoDTO userInfo;

    private String password;

    private String unionId;

    private String secretKey;

    private Integer loginType;

    private List<Long> roleIds;

    public void checkParams() {
        Validator.getInstance()
                .notNull(userInfo,"userInfo")
                .notNull(userInfo.getUserType(),"userType")
                .notNull(userInfo.getSource(),"source")
                .notNull(loginType,"loginType")
                .notBlank(Objects.equals(loginType, LoginTypeEnum.PASSWORD.getCode()),password,"password", EResultCode.PARAM_MISS)
                .notNull(Objects.equals(loginType, LoginTypeEnum.WX_APPLET.getCode()),secretKey, "openId");
    }

}
