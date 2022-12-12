package com.sbx.app.user.enums;

import lombok.Getter;

import java.util.Objects;

public enum LoginTypeEnum {

    PASSWORD(0,"用户名密码"),
    WX_APPLET(1,"微信小程序")
    ;


    @Getter
    private final Integer code;

    @Getter
    private final String desc;

    LoginTypeEnum(Integer code,String desc){
        this.code = code;
        this.desc = desc;
    }

    public static LoginTypeEnum getByCode(Integer code) {
        if (Objects.nonNull(code)) {
            for (LoginTypeEnum itemEnum : LoginTypeEnum.values()) {
                if (Objects.equals(itemEnum.getCode(),code)) {
                    return itemEnum;
                }
            }
        }
        return null;
    }
}
