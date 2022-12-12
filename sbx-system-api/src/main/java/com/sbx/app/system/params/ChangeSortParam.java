package com.sbx.app.system.params;

import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 *     说明: 变更排序请求参数
 * </p>
 *
 * @author Z.jc
 * @version 1.0.0
 * @since 2020/8/3
 */
@Data
public class ChangeSortParam implements Serializable {

    private static final long serialVersionUID = 5072833894173530056L;

    /**
     * 当前id
     */
    private Long currentId;

    /**
     * 排序类型 1向上 2向下
     */
    private Integer sortType;

}
