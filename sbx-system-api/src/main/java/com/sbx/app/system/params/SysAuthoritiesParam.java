package com.sbx.app.system.params;

import com.sbx.core.model.params.Query;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.List;

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
public class SysAuthoritiesParam extends Query {
    private static final long serialVersionUID = 1L;

    /**
     * 菜单id
     */
    private List<Long> menuIds;
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
