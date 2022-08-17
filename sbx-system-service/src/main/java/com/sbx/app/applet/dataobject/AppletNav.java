package com.sbx.app.applet.dataobject;

import com.baomidou.mybatisplus.annotation.TableName;
import com.sbx.core.mybatis.base.dataobject.BaseDO;
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
@TableName("applet_nav")
public class AppletNav extends BaseDO {

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
