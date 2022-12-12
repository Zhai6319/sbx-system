package com.sbx.app.user.service.deal;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sbx.app.user.dataobject.UserInfo;
import com.sbx.app.user.dto.UserInfoDTO;
import com.sbx.app.user.mapper.UserInfoMapper;
import com.sbx.app.user.service.query.UserInfoQuery;
import com.sbx.common.auth.PasswordUtil;
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
 * 用户信息 数据处理
 * </p>
 *
 * @author Z.jc
 * @since 2021-06-26
 */
@Service
public class UserInfoDeal extends BaseServiceImpl<UserInfoMapper, UserInfo>  {


    /**
    * 条件分页查询数据
    * @param query 查询条件
    * @return      返回分页列表
    */
    public PageResult<UserInfoDTO> queryByCondition(UserInfoQuery query){
        Page<UserInfo> page = new Page<>(query.getCurrent(),query.getSize());
        LambdaQueryWrapper<UserInfo> wrapper = this.buildWrapper(query);
        Page<UserInfo> doPage = super.page(page,wrapper);

        List<UserInfoDTO> dataList = ObjectUtils.copyList(doPage.getRecords(), UserInfoDTO.class);
        PageResult<UserInfoDTO> pageResult = new PageResult<>();
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
    public UserInfoDTO queryById(Long id){
        UserInfo userInfo = baseMapper.selectById(id);
        return ObjectUtils.copy(userInfo,UserInfoDTO.class);
    }

    /**
     * 创建用户信息
     * @param userInfoDTO   用户信息
     * @return              返回创建ID
     */
    public Long create(UserInfoDTO userInfoDTO){
        UserInfo userInfo = ObjectUtils.copy(userInfoDTO,UserInfo.class);
        if (!super.save(userInfo)) {
            throw new CustomException(EResultCode.FAILURE,"创建用户信息失败");
        }
        userInfoDTO.setId(userInfo.getId());
        return userInfoDTO.getId();
    }

    /**
     * 修改用户信息
     * @param userInfoDTO   用户信息
     */
    public void update(UserInfoDTO userInfoDTO){
        UserInfo userInfo = ObjectUtils.copy(userInfoDTO,UserInfo.class);
            userInfo.setUpdateTime(null);
            if (!super.updateById(userInfo)) {
            throw new CustomException(EResultCode.FAILURE,"修改用户信息失败");
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
     * 根据用户名获取用户信息
     * @param username  用户名
     * @param userType  用户类型
     * @return  返回用户信息
     */
    public UserInfoDTO queryByUsername(String username,Integer userType){
        UserInfoQuery query = new UserInfoQuery();
        query.setUsername(username);
        query.setUserType(userType);
        UserInfo userInfo = baseMapper.selectOne(this.buildWrapper(query));
        return ObjectUtils.copy(userInfo,UserInfoDTO.class);
    }

    /**
     * 根据手机号获取用户信息
     * @param mobile  手机号
     * @param userType  用户类型
     * @return  返回用户信息
     */
    public UserInfoDTO queryByMobile(String mobile,Integer userType){
        UserInfoQuery query = new UserInfoQuery();
        query.setMobile(mobile);
        query.setUserType(userType);
        UserInfo userInfo = baseMapper.selectOne(this.buildWrapper(query));
        return ObjectUtils.copy(userInfo,UserInfoDTO.class);
    }

    /**
     * 根据邮箱获取用户信息
     * @param email  邮箱
     * @param userType  用户类型
     * @return  返回用户信息
     */
    public UserInfoDTO queryByEmail(String email,Integer userType){
        UserInfoQuery query = new UserInfoQuery();
        query.setEmail(email);
        query.setUserType(userType);
        UserInfo userInfo = baseMapper.selectOne(this.buildWrapper(query));
        return ObjectUtils.copy(userInfo,UserInfoDTO.class);
    }

   /**
    * 构建查询条件
    * @param query    查询条件
    * @return      构建sql包装
    */
    private LambdaQueryWrapper<UserInfo> buildWrapper(UserInfoQuery query){
        LambdaQueryWrapper<UserInfo> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(StringUtil.isNotBlank(query.getUsername()),UserInfo::getUsername,query.getUsername());
        wrapper.eq(StringUtil.isNotBlank(query.getNickname()),UserInfo::getNickname,query.getNickname());
        wrapper.eq(Objects.nonNull(query.getGender()),UserInfo::getGender,query.getGender());
        wrapper.eq(StringUtil.isNotBlank(query.getMobile()),UserInfo::getMobile,query.getMobile());
        wrapper.eq(StringUtil.isNotBlank(query.getEmail()),UserInfo::getEmail,query.getEmail());
        wrapper.eq(StringUtil.isNotBlank(query.getBirthday()),UserInfo::getBirthday,query.getBirthday());
        wrapper.eq(StringUtil.isNotBlank(query.getPortrait()),UserInfo::getPortrait,query.getPortrait());
        wrapper.eq(Objects.nonNull(query.getUserType()),UserInfo::getUserType,query.getUserType());
        wrapper.eq(Objects.nonNull(query.getSuperAdmin()),UserInfo::getSuperAdmin,query.getSuperAdmin());
        wrapper.eq(Objects.nonNull(query.getSource()),UserInfo::getSource,query.getSource());
        wrapper.in(CollectionUtil.isNotEmpty(query.getIds()), BaseDO::getId,query.getIds());
        return wrapper;
    }

}