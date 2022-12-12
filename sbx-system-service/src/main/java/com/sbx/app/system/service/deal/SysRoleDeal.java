package com.sbx.app.system.service.deal;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sbx.app.system.dataobject.SysRole;
import com.sbx.app.system.dto.SysRoleDTO;
import com.sbx.app.system.mapper.SysRoleMapper;
import com.sbx.app.system.service.query.SysRoleQuery;
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
 * 系统角色 数据处理
 * </p>
 *
 * @author Z.jc
 * @since 2021-06-26
 */
@Service
public class SysRoleDeal extends BaseServiceImpl<SysRoleMapper, SysRole>  {


    /**
    * 条件分页查询数据
    * @param query 查询条件
    * @return      返回分页列表
    */
    public PageResult<SysRoleDTO> queryByCondition(SysRoleQuery query){
        Page<SysRole> page = new Page<>(query.getCurrent(),query.getSize());
        LambdaQueryWrapper<SysRole> wrapper = this.buildWrapper(query);
        Page<SysRole> doPage = super.page(page,wrapper);

        List<SysRoleDTO> dataList = ObjectUtils.copyList(doPage.getRecords(), SysRoleDTO.class);
        PageResult<SysRoleDTO> pageResult = new PageResult<>();
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
    public SysRoleDTO queryById(Long id){
        SysRole sysRole = baseMapper.selectById(id);
        return ObjectUtils.copy(sysRole,SysRoleDTO.class);
    }

    /**
     * 获取超级管理角色
     * @param roleType  角色类型
     * @return  返回结果
     */
    public List<SysRoleDTO> listSuperRole(Integer roleType){
        SysRoleQuery query = new SysRoleQuery();
        query.setRoleType(roleType);
        query.setSuperFlag(true);
        List<SysRole> roles = baseMapper.selectList(this.buildWrapper(query));
        return ObjectUtils.copyList(roles,SysRoleDTO.class);
    }

    /**
     * 创建系统角色
     * @param sysRoleDTO   系统角色
     * @return              返回创建ID
     */
    public Long create(SysRoleDTO sysRoleDTO){
        SysRole sysRole = ObjectUtils.copy(sysRoleDTO,SysRole.class);
        if (!super.save(sysRole)) {
            throw new CustomException(EResultCode.FAILURE,"创建系统角色失败");
        }
        sysRoleDTO.setId(sysRole.getId());
        return sysRoleDTO.getId();
    }

    /**
     * 修改系统角色
     * @param sysRoleDTO   系统角色
     */
    public void update(SysRoleDTO sysRoleDTO){
        SysRole sysRole = ObjectUtils.copy(sysRoleDTO,SysRole.class);
            sysRole.setUpdateTime(null);
            if (!super.updateById(sysRole)) {
            throw new CustomException(EResultCode.FAILURE,"修改系统角色失败");
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
    private LambdaQueryWrapper<SysRole> buildWrapper(SysRoleQuery query){
        LambdaQueryWrapper<SysRole> wrapper = Wrappers.lambdaQuery();
        wrapper.in(CollectionUtil.isNotEmpty(query.getIds()), BaseDO::getId,query.getIds());
        wrapper.eq(StringUtil.isNotBlank(query.getRoleName()),SysRole::getRoleName,query.getRoleName());
        wrapper.eq(StringUtil.isNotBlank(query.getRemarks()),SysRole::getRemarks,query.getRemarks());
        wrapper.eq(Objects.nonNull(query.getSuperFlag()),SysRole::getSuperFlag,query.getSuperFlag());
        wrapper.eq(Objects.nonNull(query.getRoleType()),SysRole::getRoleType,query.getRoleType());
        wrapper.like(StringUtil.isNotBlank(query.getLikeRoleName()), SysRole::getRoleName, query.getLikeRoleName());
        return wrapper;
    }

}