package com.sbx.app.system.dataobject;

import com.baomidou.mybatisplus.annotation.TableName;
import com.sbx.core.mybatis.base.dataobject.BaseDO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
* <p>
* 系统权限
* </p>
*
* @author Z.jc
* @since 2021-06-26
*/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sys_authorities")
public class SysAuthorities extends BaseDO {

    private static final long serialVersionUID = 1L;

    /**
     * 菜单id
     */
    private Long menuId;

    /**
     * 权限类型0系统1驿站
     */
    private Integer authorityType;

    /**
     * 权限
     */
    private String authority;

    /**
     * 权限方法
     */
    private String method;

    /**
     * 所属模块
     */
    private String module;


}
