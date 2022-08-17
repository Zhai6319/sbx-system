package com.sbx.app.system.enums;

import lombok.Getter;

/**
 * <p>说明：</p>
 *
 * @author Z.jc
 * @version 1.0.0
 * @since 2021/1/5
 */
public enum MenuTypeEnum {

    MENU(0,"菜单"),
    BUTTON(1,"按钮")
    ;


    @Getter
    private final Integer code;

    @Getter
    private final String desc;

    MenuTypeEnum(Integer code, String desc){
        this.code = code;
        this.desc = desc;
    }
}
