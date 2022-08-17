package com.sbx.app.system.api;

import com.sbx.app.system.dto.SysMenuDTO;
import com.sbx.app.system.params.ChangeSortParam;
import com.sbx.app.system.params.SysMenuParam;
import com.sbx.core.model.api.Response;
import com.sbx.core.model.base.result.PageResult;


/**
 * <p>
 * 系统菜单 dubbo接口
 * </p>
 *
 * @author Z.jc
 * @since 2021-06-26
 */
public interface ISysMenuApi {

    /**
     * 条件分页查询系统菜单
     * @param param 查询条件
     * @return      返回系统菜单分页列表
     */
    Response<PageResult<SysMenuDTO>> queryByCondition(SysMenuParam param);

    /**
     * 根据id查询系统菜单
     * @param id 数据id
     * @return   返回系统菜单
     */
    Response<SysMenuDTO> queryDetailById(Long id);

    /**
     * 根据路由查询数据
     * @param param 请求参数
     * @return  返回菜单信息
     */
    Response<SysMenuDTO> queryByRouter(SysMenuParam param);

    /**
     * 创建系统菜单
     * @param sysMenuDTO   系统菜单数据
     * @return              返回创建数据id
     */
    Response<Long> create(SysMenuDTO sysMenuDTO);

    /**
     * 修改系统菜单
     * @param sysMenuDTO   系统菜单数据
     * @return              返回结果
     */
    Response<Boolean> update(SysMenuDTO sysMenuDTO);

    /**
     * 排序变更
     * @param param
     * @return
     */
    Response<Boolean> changeSort(ChangeSortParam param);

    /**
     * 根据id删除系统菜单
     * @param id    数据id
     * @return      返回删除结果
     */
    Response<Boolean> delById(Long id);

}
