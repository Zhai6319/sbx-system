package com.sbx.app.system.service;

import com.sbx.app.system.dto.SysMenuDTO;
import com.sbx.app.system.params.ChangeSortParam;
import com.sbx.app.system.params.SysMenuParam;
import com.sbx.core.model.base.result.PageResult;


/**
 * <p>
 * 系统菜单 服务类
 * </p>
 *
 * @author Z.jc
 * @since 2021-06-26
 */
public interface ISysMenuService {

    /**
     * 分页查询系统菜单
     *
     * @param param 查询条件
     * @return 返回分页列表
     */
    PageResult<SysMenuDTO> page(SysMenuParam param);

    /**
     * 根据id获取系统菜单详情
     *
     * @param id 数据id
     * @return 返回系统菜单详情
     */
    SysMenuDTO detailById(Long id);

    /**
     * 根据路由查询数据
     * @param router 前端路由
     * @param ascription    归属系统
     * @return  返回菜单信息
     */
    SysMenuDTO queryByRouter(String router, Integer ascription);

    /**
     * 创建系统菜单
     *
     * @param sysMenuDTO 系统菜单数据
     * @return 返回创建数据id
     */
    Long create(SysMenuDTO sysMenuDTO);

    /**
     * 修改系统菜单
     *
     * @param sysMenuDTO 系统菜单数据
     */
    void update(SysMenuDTO sysMenuDTO);

    /**
     * 排序变更
     * @param param
     * @return
     */
    Boolean changeSort(ChangeSortParam param);

    /**
     * 根据id删除系统菜单
     *
     * @param id 数据id
     * @return 返回删除结果
     */
    Boolean delById(Long id);

}
