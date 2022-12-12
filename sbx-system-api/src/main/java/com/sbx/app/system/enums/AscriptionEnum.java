package com.sbx.app.system.enums;

import lombok.Getter;

import java.util.Objects;

/**
 * <p>说明：</p>
 *
 * @author Z.jc
 * @version 1.0.0
 * @since 2021/1/5
 */
public enum AscriptionEnum {

    WORK(0,"系统"),
    TOUR(1,"旅游端")
    ;


    @Getter
    private final Integer code;

    @Getter
    private final String desc;

    AscriptionEnum(Integer code, String desc){
        this.code = code;
        this.desc = desc;
    }

    public static AscriptionEnum getByCode(Integer code) {
        if (Objects.nonNull(code)) {
            for (AscriptionEnum ascriptionEnum : AscriptionEnum.values()) {
                if (Objects.equals(ascriptionEnum.getCode(),code)) {
                    return ascriptionEnum;
                }
            }
        }
        return null;
    }
}
