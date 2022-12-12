package com.sbx.app.applet.mapper;

import com.sbx.app.applet.dataobject.AppletNav;
import com.sbx.core.mybatis.base.mapper.IBaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 小程序菜单栏 Mapper 接口
 * </p>
 *
 * @author Z.jc
 * @since 2022-03-05
 */
@Mapper
public interface AppletNavMapper extends IBaseMapper<AppletNav> {

    /**
     * 获取排序值
     * @return 返回排序
     */
    Integer getSortValue();

}
