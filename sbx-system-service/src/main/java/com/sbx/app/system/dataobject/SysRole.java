package com.sbx.app.system.dataobject;

import com.baomidou.mybatisplus.annotation.TableName;
import com.sbx.core.mybatis.base.dataobject.BaseDO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

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
@TableName("sys_role")
public class SysRole extends BaseDO {

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


}
