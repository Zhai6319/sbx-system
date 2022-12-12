package com.sbx.app.applet.service;

import com.sbx.app.applet.dto.AppletNavDTO;
import com.sbx.app.applet.params.AppletNavParam;
import com.sbx.core.model.base.result.PageResult;


/**
 * <p>
 * 小程序菜单栏 服务类
 * </p>
 *
 * @author Z.jc
 * @since 2022-03-05
 */
public interface IAppletNavService {

    /**
     * 分页查询小程序菜单栏
     * @param param 查询条件
     * @return      返回分页列表
     */
    PageResult<AppletNavDTO> page(AppletNavParam param);

   /**
    * 根据id获取小程序菜单栏详情
    * @param id    数据id
    * @return      返回小程序菜单栏详情
    */
   AppletNavDTO detailById(Long id);

    /**
     * 创建小程序菜单栏
     * @param appletNavDTO   小程序菜单栏数据
     * @return              返回创建数据id
     */
    Long create(AppletNavDTO appletNavDTO);

    /**
     * 修改小程序菜单栏
     * @param appletNavDTO   小程序菜单栏数据
     */
    void update(AppletNavDTO appletNavDTO);

    /**
     * 根据id删除小程序菜单栏
     * @param id    数据id
     * @return      返回删除结果
     */
    Boolean delById(Long id);

}
