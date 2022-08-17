package com.sbx.app.user.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * <p>说明：</p>
 *
 * @author Z.jc
 * @version 1.0.0
 * @since 2021/6/26
 */
@Data
public class UserNavigationBarDTO implements Serializable {
    private static final long serialVersionUID = -5311316739580212263L;

    /**
     * 导航id
     */
    private Long id;

    /**
     * 前端路由
     */
    private String router;

    /**
     * 导航父级id
     */
    private Long parentId;

    /**
     * 导航名称
     */
    private String name;

    /**
     * 导航元数据
     */
    private UserNavigationMetaDTO meta;

    /**
     * 导航转发
     */
    private String redirect;

    /**
     * 导航组件
     */
    private String component;
}
