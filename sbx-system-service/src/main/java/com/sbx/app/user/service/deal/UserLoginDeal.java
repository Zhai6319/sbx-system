package com.sbx.app.user.service.deal;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sbx.app.user.dataobject.UserLogin;
import com.sbx.app.user.dto.UserLoginDTO;
import com.sbx.app.user.mapper.UserLoginMapper;
import com.sbx.app.user.service.query.UserLoginQuery;
import com.sbx.core.model.base.result.PageResult;
import com.sbx.core.model.enums.EResultCode;
import com.sbx.core.model.exception.CustomException;
import com.sbx.core.mybatis.base.service.impl.BaseServiceImpl;
import com.sbx.core.tool.util.ObjectUtils;
import com.sbx.core.tool.util.StringUtil;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * <p>
 * 用户登录 数据处理
 * </p>
 *
 * @author Z.jc
 * @since 2021-06-26
 */
@Service
public class UserLoginDeal extends BaseServiceImpl<UserLoginMapper, UserLogin>  {


    /**
    * 条件分页查询数据
    * @param query 查询条件
    * @return      返回分页列表
    */
    public PageResult<UserLoginDTO> queryByCondition(UserLoginQuery query){
        Page<UserLogin> page = new Page<>(query.getCurrent(),query.getSize());
        LambdaQueryWrapper<UserLogin> wrapper = this.buildWrapper(query);
        Page<UserLogin> doPage = super.page(page,wrapper);

        List<UserLoginDTO> dataList = ObjectUtils.copyList(doPage.getRecords(), UserLoginDTO.class);
        PageResult<UserLoginDTO> pageResult = new PageResult<>();
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
    public UserLoginDTO queryById(Long id){
        UserLogin userLogin = baseMapper.selectById(id);
        return ObjectUtils.copy(userLogin,UserLoginDTO.class);
    }

    /**
     * 创建用户登录
     * @param userLoginDTO   用户登录
     * @return              返回创建ID
     */
    public Long create(UserLoginDTO userLoginDTO){
        UserLogin userLogin = ObjectUtils.copy(userLoginDTO,UserLogin.class);
        if (!super.save(userLogin)) {
            throw new CustomException(EResultCode.FAILURE,"创建用户登录失败");
        }
        userLoginDTO.setId(userLogin.getId());
        return userLoginDTO.getId();
    }

    /**
     * 修改用户登录
     * @param userLoginDTO   用户登录
     */
    public void update(UserLoginDTO userLoginDTO){
        UserLogin userLogin = ObjectUtils.copy(userLoginDTO,UserLogin.class);
        userLogin.setUpdateTime(null);
        if (!super.updateById(userLogin)) {
            throw new CustomException(EResultCode.FAILURE,"修改用户登录失败");
        }
    }

    /**
     * 添加或修改用户登录信息
     * @param userLoginDTO 用户登录信息
     */
    public void saveOrUpdate(UserLoginDTO userLoginDTO){
        UserLogin userLogin = ObjectUtils.copy(userLoginDTO,UserLogin.class);
        if (!super.saveOrUpdate(userLogin)) {
            throw new CustomException(EResultCode.FAILURE,"更新用户登录信息失败");
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
     * 根据用户id和登录类型获取登录信息
     * @param userId    用户id
     * @param loginType 登录类型
     * @return  返回登录信息
     */
    public UserLoginDTO getByUserId(Long userId,Integer loginType){
        UserLoginQuery query = new UserLoginQuery();
        query.setLoginType(loginType);
        query.setUserId(userId);
        UserLogin userLogin = baseMapper.selectOne(this.buildWrapper(query));
        return ObjectUtils.copy(userLogin,UserLoginDTO.class);
    }

    /**
     * 根据密钥获取用户登录信息
     * @param secretKey    密钥
     * @param loginType 登录类型
     * @return  返回登录信息
     */
    public UserLoginDTO getBySecretKey(String secretKey,Integer loginType){
        UserLoginQuery query = new UserLoginQuery();
        query.setSecretKey(secretKey);
        query.setLoginType(loginType);
        UserLogin userLogin = baseMapper.selectOne(this.buildWrapper(query));
        return ObjectUtils.copy(userLogin,UserLoginDTO.class);
    }

   /**
    * 构建查询条件
    * @param query    查询条件
    * @return      构建sql包装
    */
    private LambdaQueryWrapper<UserLogin> buildWrapper(UserLoginQuery query){
        LambdaQueryWrapper<UserLogin> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(Objects.nonNull(query.getUserId()),UserLogin::getUserId,query.getUserId());
        wrapper.eq(Objects.nonNull(query.getLoginType()),UserLogin::getLoginType,query.getLoginType());
        wrapper.eq(StringUtil.isNotBlank(query.getUnionId()),UserLogin::getUnionId,query.getUnionId());
        wrapper.eq(StringUtil.isNotBlank(query.getSecretKey()),UserLogin::getSecretKey,query.getSecretKey());
        return wrapper;
    }

}