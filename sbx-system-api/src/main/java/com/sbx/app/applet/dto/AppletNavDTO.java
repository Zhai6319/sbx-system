package com.sbx.app.applet.dto;

import com.sbx.core.model.base.dto.BaseDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
* <p>
* 小程序菜单栏
* </p>
*
* @author Z.jc
* @since 2022-03-05
*/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class AppletNavDTO extends BaseDTO {
    private static final long serialVersionUID = 1L;
    /**
    * 导航名称
    */
    private String navName;
    /**
    * 图标
    */
    private String icon;
    /**
    * 当前选择图标
    */
    private String selectedIcon;
    /**
    * 页面路径
    */
    private String pagePath;
    /**
    * 业务类型
    */
    private Integer bizType;

    /**
     * 排序
     */
    private Integer sort;


}
