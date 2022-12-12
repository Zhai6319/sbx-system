package com.sbx.app.applet.api;

import com.sbx.app.applet.dto.AppletNavDTO;
import com.sbx.app.applet.params.AppletNavParam;
import com.sbx.core.model.api.Response;
import com.sbx.core.model.base.result.PageResult;


/**
 * <p>
 * 小程序菜单栏 dubbo接口
 * </p>
 *
 * @author Z.jc
 * @since 2022-03-05
 */
public interface IAppletNavApi {

    /**
     * 条件分页查询小程序菜单栏
     * @param param 查询条件
     * @return      返回小程序菜单栏分页列表
     */
    Response<PageResult<AppletNavDTO>> queryByCondition(AppletNavParam param);

    /**
     * 根据id查询小程序菜单栏
     * @param id 数据id
     * @return   返回小程序菜单栏
     */
    Response<AppletNavDTO> queryDetailById(Long id);

    /**
     * 创建小程序菜单栏
     * @param appletNavDTO   小程序菜单栏数据
     * @return              返回创建数据id
     */
    Response<Long> create(AppletNavDTO appletNavDTO);

    /**
     * 修改小程序菜单栏
     * @param appletNavDTO   小程序菜单栏数据
     * @return              返回结果
     */
    Response<Boolean> update(AppletNavDTO appletNavDTO);

    /**
     * 根据id删除小程序菜单栏
     * @param id    数据id
     * @return      返回删除结果
     */
    Response<Boolean> delById(Long id);

}
