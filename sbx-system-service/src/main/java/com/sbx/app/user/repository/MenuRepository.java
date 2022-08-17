package com.sbx.app.user.repository;

import com.google.common.collect.Lists;
import com.sbx.app.system.api.ISysMenuApi;
import com.sbx.app.system.dto.SysMenuDTO;
import com.sbx.app.system.enums.MenuTypeEnum;
import com.sbx.app.system.params.SysMenuParam;
import com.sbx.core.model.base.result.PageResult;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * <p>说明：</p>
 *
 * @author Z.jc
 * @version 1.0.0
 * @since 2021/6/26
 */
@Service
public class MenuRepository {

    @DubboReference
    private ISysMenuApi iSysMenuApi;

    /**
     * 分页查询菜单列表
     * @param param 请求参数
     * @return  返回分页列表
     */
    public PageResult<SysMenuDTO> menuPage(SysMenuParam param){
        return iSysMenuApi.queryByCondition(param).computeDataOrFailThrow();
    }

    /**
     * 根据条件查询角色菜单关联列表
     * @param param 查询条件
     * @return
     */
    public List<SysMenuDTO> menuListByCondition(SysMenuParam param){
        List<SysMenuDTO> allResultList = Lists.newArrayList();
        long size = 200L;
        long current = 1L;
        param.setSize(size);
        boolean flag = true;
        do {
            param.setCurrent(current);
            PageResult<SysMenuDTO> pageResult = this.menuPage(param);
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
     * 根据父级id获取按钮列表
     * @param parentIds 父级id列表
     * @return  返回结果
     */
    public List<SysMenuDTO> buttonsByParentIds(List<Long> parentIds,List<Long> menuIds){
        if (CollectionUtils.isEmpty(parentIds) || CollectionUtils.isEmpty(menuIds)) {
            return Collections.emptyList();
        }
        SysMenuParam param = new SysMenuParam();
        param.setIds(menuIds);
        param.setParentIds(parentIds);
        param.setMenuType(MenuTypeEnum.BUTTON.getCode());
        return this.menuListByCondition(param);
    }

    /**
     * 根据父级id获取按钮列表
     * @param parentId 父级id列表
     * @return  返回结果
     */
    public List<SysMenuDTO> buttonsByParentId(Long parentId,List<Long> userMenuIds){
        if (Objects.isNull(parentId)) {
            return Collections.emptyList();
        }
        SysMenuParam param = new SysMenuParam();
        param.setParentId(parentId);
        param.setMenuType(MenuTypeEnum.BUTTON.getCode());
        param.setIds(userMenuIds);
        return this.menuListByCondition(param);
    }



    /**
     * 获取分组map
     * @param parentIds 父级id
     * @return  返回map
     */
    public Map<Long,List<SysMenuDTO>> buttonMapBuParentIds(List<Long> parentIds,List<Long> menuIds){
        if (CollectionUtils.isEmpty(parentIds) ||CollectionUtils.isEmpty(menuIds)) {
            return Collections.emptyMap();
        }
        List<SysMenuDTO> menuList = this.buttonsByParentIds(parentIds,menuIds);
        return menuList.stream().collect(Collectors.groupingBy(SysMenuDTO::getParentId));
    }

    /**
     * 根据菜单id列表获取菜单列表
     * @param menuIds   菜单id列表
     * @return  返回菜单列表
     */
    public List<SysMenuDTO> menuListByMenuIds(List<Long> menuIds){
        if (CollectionUtils.isEmpty(menuIds)) {
            return Collections.emptyList();
        }
        SysMenuParam param = new SysMenuParam();
        param.setIds(menuIds);
        return this.menuListByCondition(param);
    }

    /**
     * 根据前端路由获取菜单
     * @param router    前端路由
     * @param ascription    归属系统
     * @return  返回菜单
     */
    public SysMenuDTO menuByRouter(String router,Integer ascription){
        SysMenuParam param = new SysMenuParam();
        param.setRouter(router);
        param.setAscription(ascription);
        return iSysMenuApi.queryByRouter(param).computeDataOrFailThrow();
    }

    /**
     * 根据菜单id获取菜单
     * @param menuId    菜单id
     * @return  返回菜单结果
     */
    public SysMenuDTO menuById(Long menuId){
        return iSysMenuApi.queryDetailById(menuId).computeDataOrFailThrow();
    }
}
