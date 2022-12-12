package com.sbx.app.system.dto;

import com.sbx.core.model.base.dto.BaseDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.List;

/**
* <p>
* 系统角色
* </p>
*
* @author Z.jc
* @since 2021-06-26
*/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class SysRoleDTO extends BaseDTO {
    private static final long serialVersionUID = 1L;
    /**
    * 角色名称
    */
    private String roleName;
    /**
    * 备注
    */
    private String remarks;
    /**
    * 是否超级角色
    */
    private Boolean superFlag;
    /**
    * 角色类型 0运营管理角色
    */
    private Integer roleType;

    /**
     * 菜单id列表
     */
    private List<Long> menuIds;

    /**
     * 半联动菜单id
     */
    private List<Long> halfMenuIds;


}
