package com.sbx.app.user.enums;

import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

public enum UserSourceEnum {

    WORK_PC(0, "WORK_PC", "运营管理系统"),
    WX_APPLET(1, "WX_APPLET", "微信小程序"),
    ALI_APPLET(2, "ALI_APPLET", "支付宝小程序"),
    IOS(3, "IOS", "苹果系统"),
    ANDROID(4, "ANDROID", "安卓系统");


    @Getter
    private final Integer code;

    @Getter
    private final String key;

    @Getter
    private final String desc;

    UserSourceEnum(Integer code, String key, String desc) {
        this.code = code;
        this.key = key;
        this.desc = desc;
    }

    public static UserSourceEnum getByCode(Integer code) {
        if (Objects.nonNull(code)) {
            for (UserSourceEnum itemEnum : UserSourceEnum.values()) {
                if (Objects.equals(itemEnum.getCode(), code)) {
                    return itemEnum;
                }
            }
        }
        return null;
    }

    public static UserSourceEnum getByKey(String key) {
        if (StringUtils.isNotBlank(key)) {
            for (UserSourceEnum itemEnum : UserSourceEnum.values()) {
                if (Objects.equals(itemEnum.getKey(), key)) {
                    return itemEnum;
                }
            }
        }
        return null;
    }
}
