package com.sbx.app.system.service.query;

import com.sbx.core.model.params.Query;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.List;

/**
* <p>
* 角色菜单表
* </p>
*
* @author Z.jc
* @since 2021-06-26
*/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class SysRoleMenuQuery extends Query {
    private static final long serialVersionUID = 1L;

    /**
     * 角色id列表
     */
    private List<Long> roleIds;
    /**
     * 角色id
     */
    private Long roleId;
    /**
     * 菜单id
     */
    private Long menuId;

    /**
     * 是否半联动
     */
    private Boolean halfFlag;


}
