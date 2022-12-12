package com.sbx.app.system.mapper;

import com.sbx.app.system.dataobject.SysMenu;
import com.sbx.core.mybatis.base.mapper.IBaseMapper;

/**
 * <p>
 * 系统菜单 Mapper 接口
 * </p>
 *
 * @author Z.jc
 * @since 2021-06-26
 */
public interface SysMenuMapper extends IBaseMapper<SysMenu> {

    /**
     * 获取最大排序值
     * @return
     */
    Integer getMaxSort();

}
