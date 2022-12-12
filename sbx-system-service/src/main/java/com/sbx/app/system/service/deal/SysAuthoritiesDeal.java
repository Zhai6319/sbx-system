package com.sbx.app.system.service.deal;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sbx.app.system.dataobject.SysAuthorities;
import com.sbx.app.system.dto.SysAuthoritiesDTO;
import com.sbx.app.system.mapper.SysAuthoritiesMapper;
import com.sbx.app.system.service.query.SysAuthoritiesQuery;
import com.sbx.core.model.base.result.PageResult;
import com.sbx.core.model.enums.EResultCode;
import com.sbx.core.model.exception.CustomException;
import com.sbx.core.mybatis.base.service.impl.BaseServiceImpl;
import com.sbx.core.tool.util.CollectionUtil;
import com.sbx.core.tool.util.ObjectUtils;
import com.sbx.core.tool.util.StringUtil;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * <p>
 * 系统权限 数据处理
 * </p>
 *
 * @author Z.jc
 * @since 2021-06-26
 */
@Service
public class SysAuthoritiesDeal extends BaseServiceImpl<SysAuthoritiesMapper, SysAuthorities>  {


    /**
    * 条件分页查询数据
    * @param query 查询条件
    * @return      返回分页列表
    */
    public PageResult<SysAuthoritiesDTO> queryByCondition(SysAuthoritiesQuery query){
        Page<SysAuthorities> page = new Page<>(query.getCurrent(),query.getSize());
        LambdaQueryWrapper<SysAuthorities> wrapper = this.buildWrapper(query);
        Page<SysAuthorities> doPage = super.page(page,wrapper);

        List<SysAuthoritiesDTO> dataList = ObjectUtils.copyList(doPage.getRecords(), SysAuthoritiesDTO.class);
        PageResult<SysAuthoritiesDTO> pageResult = new PageResult<>();
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
    public SysAuthoritiesDTO queryById(Long id){
        SysAuthorities sysAuthorities = baseMapper.selectById(id);
        return ObjectUtils.copy(sysAuthorities,SysAuthoritiesDTO.class);
    }

    /**
     * 创建系统权限
     * @param sysAuthoritiesDTO   系统权限
     * @return              返回创建ID
     */
    public Long create(SysAuthoritiesDTO sysAuthoritiesDTO){
        SysAuthorities sysAuthorities = ObjectUtils.copy(sysAuthoritiesDTO,SysAuthorities.class);
        if (!super.save(sysAuthorities)) {
            throw new CustomException(EResultCode.FAILURE,"创建系统权限失败");
        }
        sysAuthoritiesDTO.setId(sysAuthorities.getId());
        return sysAuthoritiesDTO.getId();
    }

    /**
     * 批量添加菜单权限
     * @param authorities   权限列表
     * @return              返回创建结果
     */
    public Boolean batchCreateAuthority(List<SysAuthoritiesDTO> authorities){
        List<SysAuthorities> sysAuthorities = ObjectUtils.copyList(authorities,SysAuthorities.class);
        return super.saveBatch(sysAuthorities);
    }

    /**
     * 修改系统权限
     * @param sysAuthoritiesDTO   系统权限
     */
    public void update(SysAuthoritiesDTO sysAuthoritiesDTO){
        SysAuthorities sysAuthorities = ObjectUtils.copy(sysAuthoritiesDTO,SysAuthorities.class);
            sysAuthorities.setUpdateTime(null);
            if (!super.updateById(sysAuthorities)) {
            throw new CustomException(EResultCode.FAILURE,"修改系统权限失败");
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
     * 根据menuId删除权限数据
     * @param menuId    菜单id
     * @return          返回删除结果
     */
    public Boolean deleteByMenuId(Long menuId,String model){
        return super.remove(Wrappers.<SysAuthorities>lambdaUpdate()
                .eq(SysAuthorities::getMenuId,menuId)
                .eq(SysAuthorities::getModule,model)
        );
    }
   /**
    * 构建查询条件
    * @param query    查询条件
    * @return      构建sql包装
    */
    private LambdaQueryWrapper<SysAuthorities> buildWrapper(SysAuthoritiesQuery query){
        LambdaQueryWrapper<SysAuthorities> wrapper = Wrappers.lambdaQuery();
        wrapper.in(CollectionUtil.isNotEmpty(query.getMenuIds()),SysAuthorities::getMenuId,query.getMenuIds());
        wrapper.eq(Objects.nonNull(query.getMenuId()),SysAuthorities::getMenuId,query.getMenuId());
        wrapper.eq(Objects.nonNull(query.getAuthorityType()),SysAuthorities::getAuthorityType,query.getAuthorityType());
        wrapper.eq(StringUtil.isNotBlank(query.getAuthority()),SysAuthorities::getAuthority,query.getAuthority());
        wrapper.eq(StringUtil.isNotBlank(query.getMethod()),SysAuthorities::getMethod,query.getMethod());
        wrapper.eq(StringUtil.isNotBlank(query.getModule()),SysAuthorities::getModule,query.getModule());
        return wrapper;
    }

}