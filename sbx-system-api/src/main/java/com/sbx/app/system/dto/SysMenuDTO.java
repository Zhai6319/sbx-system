package com.sbx.app.system.dto;

import com.sbx.core.model.base.dto.BaseDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
* <p>
* 系统菜单
* </p>
*
* @author Z.jc
* @since 2021-06-26
*/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class SysMenuDTO extends BaseDTO {
    private static final long serialVersionUID = 1L;
    /**
    * 父级id 0为当前菜单一级
    */
    private Long parentId;
    /**
    * 菜单类型0菜单1按钮
    */
    private Integer menuType;
    /**
    * 归属 0系统
    */
    private Integer ascription;
    /**
    * 菜单标题
    */
    private String menuTitle;
    /**
    * 前端路由
    */
    private String router;
    /**
    * 菜单编码
    */
    private String menuCode;
    /**
    * 前端组件
    */
    private String component;
    /**
    * 转发
    */
    private String redirect;
    /**
    * 图标
    */
    private String icon;
    /**
    * 菜单等级1一级2二级3三级
    */
    private Integer menuLevel;
    /**
    * 是否展示0否1是
    */
    private Boolean showFlag;

    private Integer sort;


}
