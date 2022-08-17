package com.sbx.app.user.repository;

import com.google.common.collect.Lists;
import com.sbx.app.system.api.ISysRoleApi;
import com.sbx.app.system.api.ISysRoleMenuApi;
import com.sbx.app.system.dto.SysRoleDTO;
import com.sbx.app.system.dto.SysRoleMenuDTO;
import com.sbx.app.system.params.SysRoleMenuParam;
import com.sbx.app.system.params.SysRoleParam;
import com.sbx.core.model.base.result.PageResult;
import com.sbx.core.tool.util.CollectionUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * <p>说明：</p>
 *
 * @author Z.jc
 * @version 1.0.0
 * @since 2021/6/26
 */
@Service
public class RoleRepository {

    @DubboReference
    private ISysRoleApi iSysRoleApi;
    @DubboReference
    private ISysRoleMenuApi iSysRoleMenuApi;

    /**
     * 分页查询角色列表
     * @param param 请求参数
     * @return  返回结果
     */
    public PageResult<SysRoleDTO> rolePage(SysRoleParam param){
        return iSysRoleApi.queryByCondition(param).computeDataOrFailThrow();
    }

    /**
     * 根据条件查询列表
     * @param param 查询条件
     * @return
     */
    public List<SysRoleDTO> roleListByCondition(SysRoleParam param){
        List<SysRoleDTO> allResultList = Lists.newArrayList();
        long size = 200L;
        long current = 1L;
        param.setSize(size);
        boolean flag = true;
        do {
            param.setCurrent(current);
            PageResult<SysRoleDTO> pageResult = this.rolePage(param);
            // 为空不需要更新，跳出循环;不为空则更新
            if (CollectionUtils.isEmpty(pageResult.getRecords())) {
                flag = false;
            } else {
                allResultList.addAll(pageResult.getRecords());
                // 查询数量不等于分页数量即为最后一页，跳出循环
                if (size != pageResult.getRecords().size()) {
                    flag = false;
                }
            }
            current++;
            // 循环次数大于50退出循环，实际总数最多10W左右
        } while (flag && current < 50);
        return allResultList;
    }

    /**
     * 根据角色id列表获取角色列表
     * @param roleIds   角色id列表
     * @return  返回角色列表
     */
    public List<SysRoleDTO> roleListByRoleIds(List<Long> roleIds){
        if (CollectionUtil.isEmpty(roleIds)) {
            return Collections.emptyList();
        }
        SysRoleParam param = new SysRoleParam();
        param.setIds(roleIds);
        return this.roleListByCondition(param);
    }

    /**
     * 分页查询角色菜单关联列表
     * @param param 请求参数
     * @return  返回结果
     */
    public PageResult<SysRoleMenuDTO> roleMenuPage(SysRoleMenuParam param){
        return iSysRoleMenuApi.queryByCondition(param).computeDataOrFailThrow();
    }

    /**
     * 根据条件查询角色菜单关联列表
     * @param param 查询条件
     * @return
     */
    public List<SysRoleMenuDTO> roleMenuListByCondition(SysRoleMenuParam param){
        List<SysRoleMenuDTO> allResultList = Lists.newArrayList();
        long size = 200L;
        long current = 1L;
        param.setSize(size);
        boolean flag = true;
        do {
            param.setCurrent(current);
            PageResult<SysRoleMenuDTO> pageResult = this.roleMenuPage(param);
            // 为空不需要更新，跳出循环;不为空则更新
            if (CollectionUtils.isEmpty(pageResult.getRecords())) {
                flag = false;
            } else {
                allResultList.addAll(pageResult.getRecords());
                // 查询数量不等于分页数量即为最后一页，跳出循环
                if (size != pageResult.getRecords().size()) {
                    flag = false;
                }
            }
            current++;
            // 循环次数大于50退出循环，实际总数最多10W左右
        } while (flag && current < 50);
        return allResultList;
    }

    /**
     * 根据角色id列表获取角色菜单关联列表
     * @param roleIds   角色id列表
     * @return  返回角色菜单关联列表
     */
    public List<SysRoleMenuDTO> roleMenuListByRoleIds(List<Long> roleIds){
        if (CollectionUtil.isEmpty(roleIds)) {
            return Collections.emptyList();
        }
        SysRoleMenuParam param = new SysRoleMenuParam();
        param.setRoleIds(roleIds);
        return this.roleMenuListByCondition(param);
    }


}
