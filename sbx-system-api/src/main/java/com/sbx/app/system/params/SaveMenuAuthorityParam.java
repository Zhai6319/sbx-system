package com.sbx.app.system.params;

import com.sbx.app.system.dto.SysAuthoritiesDTO;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * <p>说明：</p>
 *
 * @author Z.jc
 * @version 1.0.0
 * @since 2021/1/25
 */
@Data
public class SaveMenuAuthorityParam implements Serializable {

    private static final long serialVersionUID = -895681169712617171L;

    private Long menuId;

    /**
     * 所属模块
     */
    private String module;

    private List<SysAuthoritiesDTO> sysAuthorities;
}
