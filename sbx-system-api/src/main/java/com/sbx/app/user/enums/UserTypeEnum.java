package com.sbx.app.user.enums;

import lombok.Getter;

import java.util.Objects;

/**
 * <p>说明：</p>
 *
 * @author Z.jc
 * @version 1.0.0
 * @since 2020/12/26
 */
public enum UserTypeEnum {

    ADMIN(0,"系统用户"),
    MALL(1,"商城用户"),
    SASS(2,"sass用户"),
    SYSTEM(99,"系统")
    ;

    @Getter
    private final Integer code;

    @Getter
    private final String desc;

    UserTypeEnum(Integer code, String desc){
        this.code = code;
        this.desc = desc;
    }

    public static UserTypeEnum findByType(Integer type){
        if (Objects.nonNull(type)) {
            for (UserTypeEnum value : UserTypeEnum.values()) {
                if (Objects.equals(type,value.getCode())) {
                    return value;
                }
            }
        }
        return null;
    }
}
