package com.sbx.app.system.service.deal;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sbx.app.system.dataobject.SysMenu;
import com.sbx.app.system.dto.SysMenuDTO;
import com.sbx.app.system.mapper.SysMenuMapper;
import com.sbx.app.system.service.query.SysMenuQuery;
import com.sbx.core.model.base.result.PageResult;
import com.sbx.core.model.enums.EResultCode;
import com.sbx.core.model.exception.CustomException;
import com.sbx.core.mybatis.base.dataobject.BaseDO;
import com.sbx.core.mybatis.base.service.impl.BaseServiceImpl;
import com.sbx.core.tool.util.CollectionUtil;
import com.sbx.core.tool.util.ObjectUtils;
import com.sbx.core.tool.util.StringUtil;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * <p>
 * 系统菜单 数据处理
 * </p>
 *
 * @author Z.jc
 * @since 2021-06-26
 */
@Service
public class SysMenuDeal extends BaseServiceImpl<SysMenuMapper, SysMenu>  {


    /**
    * 条件分页查询数据
    * @param query 查询条件
    * @return      返回分页列表
    */
    public PageResult<SysMenuDTO> queryByCondition(SysMenuQuery query){
        Page<SysMenu> page = new Page<>(query.getCurrent(),query.getSize());
        LambdaQueryWrapper<SysMenu> wrapper = this.buildWrapper(query);
        wrapper.orderByAsc(SysMenu::getSort);
        Page<SysMenu> doPage = super.page(page,wrapper);

        List<SysMenuDTO> dataList = ObjectUtils.copyList(doPage.getRecords(), SysMenuDTO.class);
        PageResult<SysMenuDTO> pageResult = new PageResult<>();
        pageResult.setRecords(dataList);
        pageResult.setTotal(doPage.getTotal());
        pageResult.setSize(query.getSize());
        pageResult.setPages(doPage.getPages());
        pageResult.setCurrent(query.getCurrent());
        pageResult.setRecords(dataList);
        return pageResult;
    }

    /**
    * 根据id查询数据
    * @param id    数据id
    * @return      返回数据
    */
    public SysMenuDTO queryById(Long id){
        SysMenu sysMenu = baseMapper.selectById(id);
        return ObjectUtils.copy(sysMenu,SysMenuDTO.class);
    }

    /**
     * 根据路由获取角色菜单
     * @param router    路由
     * @param ascription    归属系统
     * @return  返回菜单信息
     */
    public SysMenuDTO queryByRouter(String router,Integer ascription){
        SysMenuQuery query = new SysMenuQuery();
        query.setRouter(router);
        query.setAscription(ascription);
        SysMenu sysMenu = baseMapper.selectOne(this.buildWrapper(query));
        return ObjectUtils.copy(sysMenu,SysMenuDTO.class);
    }

    public SysMenuDTO queryByChangeData(Integer sort,Integer sortType,Integer level,Long parentId,Integer ascription){
        if (Objects.equals(sortType,1)) {
            SysMenu sysMenu = baseMapper.selectOne(Wrappers.<SysMenu>lambdaQuery()
                    .eq(SysMenu::getAscription,ascription)
                    .eq(SysMenu::getParentId,parentId)
                    .eq(SysMenu::getMenuLevel,level)
                    .lt(SysMenu::getSort,sort)
                    .orderByDesc(SysMenu::getSort)
                    .last("limit 1"));
            return ObjectUtils.copy(sysMenu,SysMenuDTO.class);
        }
        if (Objects.equals(sortType,2)) {
            SysMenu sysMenu = baseMapper.selectOne(Wrappers.<SysMenu>lambdaQuery()
                    .eq(SysMenu::getAscription,ascription)
                    .eq(SysMenu::getParentId,parentId)
                    .eq(SysMenu::getMenuLevel,level)
                    .gt(SysMenu::getSort,sort)
                    .orderByAsc(SysMenu::getSort)
                    .last("limit 1"));
            return ObjectUtils.copy(sysMenu,SysMenuDTO.class);
        }
        return null;

    }

    /**
     * 根据归属和前端路径获取菜单信息
     * @param ascription    {@link com.sbx.app.system.enums.AscriptionEnum}
     * @param router          前端路径
     * @return              返回菜单信息
     */
    public SysMenuDTO queryByAscriptionAndPath(Integer ascription,String router){
        SysMenu sysMenu = baseMapper.selectOne(Wrappers.<SysMenu>lambdaQuery()
                .eq(SysMenu::getAscription,ascription)
                .eq(SysMenu::getRouter,router)
                .ne(SysMenu::getRouter,"")
        );
        return ObjectUtils.copy(sysMenu,SysMenuDTO.class);
    }

    /**
     * 创建系统菜单
     * @param sysMenuDTO   系统菜单
     * @return              返回创建ID
     */
    public Long create(SysMenuDTO sysMenuDTO){
        int maxSort = baseMapper.getMaxSort();
        SysMenu sysMenu = ObjectUtils.copy(sysMenuDTO,SysMenu.class);
        sysMenu.setSort(maxSort+1);
        if (!super.save(sysMenu)) {
            throw new CustomException(EResultCode.FAILURE,"创建系统菜单失败");
        }
        sysMenuDTO.setId(sysMenu.getId());
        return sysMenuDTO.getId();
    }

    /**
     * 修改系统菜单
     * @param sysMenuDTO   系统菜单
     */
    public void update(SysMenuDTO sysMenuDTO){
        SysMenu sysMenu = ObjectUtils.copy(sysMenuDTO,SysMenu.class);
        sysMenu.setUpdateTime(null);
        if (!super.updateById(sysMenu)) {
            throw new CustomException(EResultCode.FAILURE,"修改系统菜单失败");
        }
    }

    /**
     * 根据ID删除数据
     * @param id    数据ID
     * @return      返回删除结果
     */
    public Boolean delById(Long id){
        return super.removeById(id);
    }

   /**
    * 构建查询条件
    * @param query    查询条件
    * @return      构建sql包装
    */
    private LambdaQueryWrapper<SysMenu> buildWrapper(SysMenuQuery query){
        LambdaQueryWrapper<SysMenu> wrapper = Wrappers.lambdaQuery();
        wrapper.in(CollectionUtil.isNotEmpty(query.getIds()), BaseDO::getId,query.getIds());
        wrapper.in(CollectionUtil.isNotEmpty(query.getParentIds()),SysMenu::getParentId,query.getParentIds());
        wrapper.eq(Objects.nonNull(query.getParentId()),SysMenu::getParentId,query.getParentId());
        wrapper.eq(Objects.nonNull(query.getMenuType()),SysMenu::getMenuType,query.getMenuType());
        wrapper.eq(Objects.nonNull(query.getAscription()),SysMenu::getAscription,query.getAscription());
        wrapper.eq(StringUtil.isNotBlank(query.getMenuTitle()),SysMenu::getMenuTitle,query.getMenuTitle());
        wrapper.eq(StringUtil.isNotBlank(query.getRouter()),SysMenu::getRouter,query.getRouter());
        wrapper.eq(StringUtil.isNotBlank(query.getMenuCode()),SysMenu::getMenuCode,query.getMenuCode());
        wrapper.eq(StringUtil.isNotBlank(query.getComponent()),SysMenu::getComponent,query.getComponent());
        wrapper.eq(StringUtil.isNotBlank(query.getRedirect()),SysMenu::getRedirect,query.getRedirect());
        wrapper.eq(StringUtil.isNotBlank(query.getIcon()),SysMenu::getIcon,query.getIcon());
        wrapper.eq(Objects.nonNull(query.getMenuLevel()),SysMenu::getMenuLevel,query.getMenuLevel());
        wrapper.eq(Objects.nonNull(query.getShowFlag()),SysMenu::getShowFlag,query.getShowFlag());
        return wrapper;
    }

}