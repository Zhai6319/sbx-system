package com.sbx.app.system.service.deal;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sbx.app.system.dataobject.SysRoleMenu;
import com.sbx.app.system.dto.SysRoleMenuDTO;
import com.sbx.app.system.mapper.SysRoleMenuMapper;
import com.sbx.app.system.service.query.SysRoleMenuQuery;
import com.sbx.core.model.base.result.PageResult;
import com.sbx.core.model.enums.EResultCode;
import com.sbx.core.model.exception.CustomException;
import com.sbx.core.mybatis.base.service.impl.BaseServiceImpl;
import com.sbx.core.tool.util.CollectionUtil;
import com.sbx.core.tool.util.ObjectUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * <p>
 * 角色菜单表 数据处理
 * </p>
 *
 * @author Z.jc
 * @since 2021-06-26
 */
@Service
public class SysRoleMenuDeal extends BaseServiceImpl<SysRoleMenuMapper, SysRoleMenu>  {


    /**
    * 条件分页查询数据
    * @param query 查询条件
    * @return      返回分页列表
    */
    public PageResult<SysRoleMenuDTO> queryByCondition(SysRoleMenuQuery query){
        Page<SysRoleMenu> page = new Page<>(query.getCurrent(),query.getSize());
        LambdaQueryWrapper<SysRoleMenu> wrapper = this.buildWrapper(query);
        Page<SysRoleMenu> doPage = super.page(page,wrapper);

        List<SysRoleMenuDTO> dataList = ObjectUtils.copyList(doPage.getRecords(), SysRoleMenuDTO.class);
        PageResult<SysRoleMenuDTO> pageResult = new PageResult<>();
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
    public SysRoleMenuDTO queryById(Long id){
        SysRoleMenu sysRoleMenu = baseMapper.selectById(id);
        return ObjectUtils.copy(sysRoleMenu,SysRoleMenuDTO.class);
    }

    /**
     * 根据角色id获取角色菜单列表
     * @param roleId    角色id
     * @return  返回角色菜单列表
     */
    public List<SysRoleMenuDTO> queryByRoleId(Long roleId) {
        SysRoleMenuQuery query = new SysRoleMenuQuery();
        query.setRoleId(roleId);
        List<SysRoleMenu> roleMenus = baseMapper.selectList(this.buildWrapper(query));
        return ObjectUtils.copyList(roleMenus,SysRoleMenuDTO.class);
    }

    /**
     * 创建角色菜单表
     * @param sysRoleMenuDTO   角色菜单表
     * @return              返回创建ID
     */
    public Long create(SysRoleMenuDTO sysRoleMenuDTO){
        SysRoleMenu sysRoleMenu = ObjectUtils.copy(sysRoleMenuDTO,SysRoleMenu.class);
        if (!super.save(sysRoleMenu)) {
            throw new CustomException(EResultCode.FAILURE,"创建角色菜单表失败");
        }
        sysRoleMenuDTO.setId(sysRoleMenu.getId());
        return sysRoleMenuDTO.getId();
    }

    /**
     * 批量创建角色菜单
     * @param roleMenuList  请求列表
     */
    public void batchCreate(List<SysRoleMenuDTO> roleMenuList){
        List<SysRoleMenu> roleMenus = ObjectUtils.copyList(roleMenuList,SysRoleMenu.class);
        if (!super.saveBatch(roleMenus)) {
            throw new CustomException(EResultCode.FAILURE,"批量创建角色菜单表失败");
        }
    }

    /**
     * 修改角色菜单表
     * @param sysRoleMenuDTO   角色菜单表
     */
    public void update(SysRoleMenuDTO sysRoleMenuDTO){
        SysRoleMenu sysRoleMenu = ObjectUtils.copy(sysRoleMenuDTO,SysRoleMenu.class);
            sysRoleMenu.setUpdateTime(null);
            if (!super.updateById(sysRoleMenu)) {
            throw new CustomException(EResultCode.FAILURE,"修改角色菜单表失败");
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

    public Boolean delByRoleId(Long roleId){
        return super.remove(Wrappers.<SysRoleMenu>lambdaQuery()
                .eq(SysRoleMenu::getRoleId,roleId)
        );
    }
   /**
    * 构建查询条件
    * @param query    查询条件
    * @return      构建sql包装
    */
    private LambdaQueryWrapper<SysRoleMenu> buildWrapper(SysRoleMenuQuery query){
        LambdaQueryWrapper<SysRoleMenu> wrapper = Wrappers.lambdaQuery();
        wrapper.in(CollectionUtil.isNotEmpty(query.getRoleIds()),SysRoleMenu::getRoleId,query.getRoleIds());
        wrapper.eq(Objects.nonNull(query.getRoleId()),SysRoleMenu::getRoleId,query.getRoleId());
        wrapper.eq(Objects.nonNull(query.getMenuId()),SysRoleMenu::getMenuId,query.getMenuId());
        wrapper.eq(Objects.nonNull(query.getHalfFlag()), SysRoleMenu::getHalfFlag, query.getHalfFlag());
        return wrapper;
    }

}