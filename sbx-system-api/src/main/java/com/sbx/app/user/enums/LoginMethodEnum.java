package com.sbx.app.user.enums;

import lombok.Getter;

import java.util.Objects;

public enum LoginMethodEnum {

    USERNAME(0,"用户名密码"),
    MOBILE_PASSWORD(1,"手机号密码"),
    MOBILE_SMS_CODE(2,"手机号短信验证码"),
    EMAIL_PASSWORD(3,"邮箱密码"),
    EMAIL_CODE(4,"邮箱验证码"),
    WX_APPLET(5,"微信小程序")
    ;


    @Getter
    private final Integer code;

    @Getter
    private final String desc;

    LoginMethodEnum(Integer code, String desc){
        this.code = code;
        this.desc = desc;
    }

    public static LoginMethodEnum getByCode(Integer code) {
        if (Objects.nonNull(code)) {
            for (LoginMethodEnum itemEnum : LoginMethodEnum.values()) {
                if (Objects.equals(itemEnum.getCode(),code)) {
                    return itemEnum;
                }
            }
        }
        return null;
    }
}
