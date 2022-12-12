package com.sbx.app.user.service.deal;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.collect.Lists;
import com.sbx.app.user.dataobject.UserRole;
import com.sbx.app.user.dto.UserRoleDTO;
import com.sbx.app.user.mapper.UserRoleMapper;
import com.sbx.app.user.service.query.UserRoleQuery;
import com.sbx.core.model.base.result.PageResult;
import com.sbx.core.model.enums.EResultCode;
import com.sbx.core.model.exception.CustomException;
import com.sbx.core.mybatis.base.service.impl.BaseServiceImpl;
import com.sbx.core.tool.util.CollectionUtil;
import com.sbx.core.tool.util.ObjectUtils;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * <p>
 * 用户角色关联表 数据处理
 * </p>
 *
 * @author Z.jc
 * @since 2021-06-26
 */
@Service
public class UserRoleDeal extends BaseServiceImpl<UserRoleMapper, UserRole>  {


    /**
    * 条件分页查询数据
    * @param query 查询条件
    * @return      返回分页列表
    */
    public PageResult<UserRoleDTO> queryByCondition(UserRoleQuery query){
        Page<UserRole> page = new Page<>(query.getCurrent(),query.getSize());
        LambdaQueryWrapper<UserRole> wrapper = this.buildWrapper(query);
        Page<UserRole> doPage = super.page(page,wrapper);

        List<UserRoleDTO> dataList = ObjectUtils.copyList(doPage.getRecords(), UserRoleDTO.class);
        PageResult<UserRoleDTO> pageResult = new PageResult<>();
        pageResult.setRecords(dataList);
        pageResult.setTotal(doPage.getTotal());
        pageResult.setSize(query.getSize());
        pageResult.setPages(doPage.getPages());
        pageResult.setCurrent(query.getCurrent());
        pageResult.setRecords(dataList);
        return pageResult;
    }

    /**
     * 根据用户id获取用户角色绑定列表
     * @param userId    用户id
     * @return  返回用户角色绑定列表
     */
    public List<UserRoleDTO> listByUserId(Long userId){
        UserRoleQuery query = new UserRoleQuery();
        query.setUserId(userId);
        List<UserRole> userRoles = baseMapper.selectList(this.buildWrapper(query));
        return ObjectUtils.copyList(userRoles,UserRoleDTO.class);
    }

    /**
     * 根据用户id列表获取用户角色绑定列表
     * @param userIds    用户id列表
     * @return  返回用户角色绑定列表
     */
    public List<UserRoleDTO> listByUserIds(List<Long> userIds){
        if (CollectionUtil.isEmpty(userIds)) {
            return Collections.emptyList();
        }
        UserRoleQuery query = new UserRoleQuery();
        query.setUserIds(userIds);
        List<UserRole> userRoles = baseMapper.selectList(this.buildWrapper(query));
        return ObjectUtils.copyList(userRoles,UserRoleDTO.class);
    }

    /**
     * 根据用户id列表获取用户角色绑定列表
     * @param userIds    用户id列表
     * @return  返回用户角色绑定列表
     */
    public Map<Long,List<UserRoleDTO>> groupMapByUserIds(List<Long> userIds){
        if (CollectionUtil.isEmpty(userIds)) {
            return Collections.emptyMap();
        }
        List<UserRoleDTO> userRoles = this.listByUserIds(userIds);
        return userRoles.stream().collect(Collectors.groupingBy(UserRoleDTO::getUserId));
    }

    /**
    * 根据id查询数据
    * @param id    数据id
    * @return      返回数据
    */
    public UserRoleDTO queryById(Long id){
        UserRole userRole = baseMapper.selectById(id);
        return ObjectUtils.copy(userRole,UserRoleDTO.class);
    }

    /**
     * 创建用户角色关联表
     * @param userRoleDTO   用户角色关联表
     * @return              返回创建ID
     */
    public Long create(UserRoleDTO userRoleDTO){
        UserRole userRole = ObjectUtils.copy(userRoleDTO,UserRole.class);
        if (!super.save(userRole)) {
            throw new CustomException(EResultCode.FAILURE,"创建用户角色关联表失败");
        }
        userRoleDTO.setId(userRole.getId());
        return userRoleDTO.getId();
    }

    /**
     * 批量创建用户角色关联数据
     * @param userRoles 用户角色列表
     */
    public void batchCreate(List<UserRoleDTO> userRoles){
        List<UserRole> userRoleList = ObjectUtils.copyList(userRoles,UserRole.class);
        if (!super.saveBatch(userRoleList)) {
            throw new CustomException(EResultCode.FAILURE,"批量创建用户角色关联表失败");
        }
    }

    /**
     * 根据角色id和用户id创建用户角色
     * @param roleIds   角色id
     * @param userId    用户id
     */
    public void batchCreateByUserId(List<Long> roleIds,Long userId){
        if (CollectionUtil.isNotEmpty(roleIds)) {
            List<UserRoleDTO> userRoles = Lists.newArrayList();
            for (Long roleId : roleIds) {
                UserRoleDTO userRole = new UserRoleDTO();
                userRole.setUserId(userId);
                userRole.setRoleId(roleId);
                userRoles.add(userRole);
            }
            this.batchCreate(userRoles);
        }
    }

    /**
     * 根据用户id删除角色绑定关系
     * @param userId    用户id
     */
    public void delByUserId(Long userId){
        UserRoleQuery query = new UserRoleQuery();
        query.setUserId(userId);
        baseMapper.delete(this.buildWrapper(query));
    }

    /**
     * 修改用户角色关联表
     * @param userRoleDTO   用户角色关联表
     */
    public void update(UserRoleDTO userRoleDTO){
        UserRole userRole = ObjectUtils.copy(userRoleDTO,UserRole.class);
            userRole.setUpdateTime(null);
            if (!super.updateById(userRole)) {
            throw new CustomException(EResultCode.FAILURE,"修改用户角色关联表失败");
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
    private LambdaQueryWrapper<UserRole> buildWrapper(UserRoleQuery query){
        LambdaQueryWrapper<UserRole> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(Objects.nonNull(query.getUserId()),UserRole::getUserId,query.getUserId());
        wrapper.eq(Objects.nonNull(query.getRoleId()),UserRole::getRoleId,query.getRoleId());
        wrapper.in(CollectionUtil.isNotEmpty(query.getUserIds()),UserRole::getUserId,query.getUserIds());
        return wrapper;
    }

}