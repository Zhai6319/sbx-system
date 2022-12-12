package com.sbx.app.user.service.impl;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.api.WxMaUserService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import cn.binarywang.wx.miniapp.bean.WxMaPhoneNumberInfo;
import com.sbx.app.user.dataobject.UserInfo;
import com.sbx.app.user.dto.UserAuthDTO;
import com.sbx.app.user.dto.UserInfoDTO;
import com.sbx.app.user.dto.UserLoginDTO;
import com.sbx.app.user.enums.LoginMethodEnum;
import com.sbx.app.user.enums.LoginTypeEnum;
import com.sbx.app.user.enums.UserSourceEnum;
import com.sbx.app.user.enums.UserTypeEnum;
import com.sbx.app.user.params.UserAuthParam;
import com.sbx.app.user.params.UserLoginParam;
import com.sbx.app.user.service.IUserLoginService;
import com.sbx.app.user.service.deal.UserInfoDeal;
import com.sbx.app.user.service.deal.UserLoginDeal;
import com.sbx.app.user.service.executor.user.create.CreateUserExecutorRegister;
import com.sbx.app.user.service.executor.user.create.model.CreateUserModel;
import com.sbx.app.user.service.query.UserLoginQuery;
import com.sbx.common.auth.PasswordUtil;
import com.sbx.common.auth.TokenHelper;
import com.sbx.common.auth.model.AuthUser;
import com.sbx.core.model.api.Response;
import com.sbx.core.model.base.result.PageResult;
import com.sbx.core.model.exception.CustomException;
import com.sbx.core.model.exception.SecurityException;
import com.sbx.core.model.validator.Validator;
import com.sbx.core.sms.helper.SmsHelper;
import com.sbx.core.tool.util.ObjectUtils;
import com.sbx.core.tool.util.StringUtil;
import com.sbx.core.wx.config.WxMaConfiguration;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Objects;
import java.util.UUID;

/**
 * <p>
 * 用户登录 服务实现类
 * </p>
 *
 * @author Z.jc
 * @since 2021-06-26
 */
@Slf4j
@Service
@RefreshScope
public class UserLoginServiceImpl implements IUserLoginService {

    @Resource
    private UserLoginDeal userLoginDeal;
    @Resource
    private UserInfoDeal userInfoDeal;
    @Resource
    private TokenHelper tokenHelper;
    @Resource
    private SmsHelper smsHelper;

    @Value("${sbx.wx.applet.app-id}")
    private String appId;

    @Override
    public PageResult<UserLoginDTO> page(UserLoginParam param) {
        UserLoginQuery query = ObjectUtils.copy(param,UserLoginQuery.class);
        return userLoginDeal.queryByCondition(query);
    }

    @Override
    public UserLoginDTO detailById(Long id) {
        return userLoginDeal.queryById(id);
    }

    @Override
    public Long create(UserLoginDTO userLoginDTO) {
        return userLoginDeal.create(userLoginDTO);
    }

    @Override
    public void update(UserLoginDTO userLoginDTO) {
        userLoginDeal.update(userLoginDTO);
    }

    @Override
    public Boolean delById(Long id) {
        return userLoginDeal.delById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public UserAuthDTO userAuth(UserAuthParam param) {
        Validator.getInstance()
                .notNull(param.getUserType(),"userType")
                .notNull(param.getLoginType(),"loginType")
                .notNull(param.getLoginMethod(),"loginMethod");
        LoginMethodEnum loginMethod = LoginMethodEnum.getByCode(param.getLoginMethod());
        if (Objects.isNull(loginMethod)) {
            throw new SecurityException("登录方式错误");
        }
        switch (loginMethod) {
            case USERNAME:{
                //用户名密码登录
                return this.usernameLogin(param);
            }
            case MOBILE_PASSWORD:{
                //手机号密码登录
                return this.mobilePasswordLogin(param);
            }
            case MOBILE_SMS_CODE:{
                // 手机号验证码登录
                return this.mobileSmsCodeLogin(param);
            }
            case EMAIL_PASSWORD:{
                // 邮箱密码登录
                return this.emailPasswordLogin(param);
            }
            case WX_APPLET:{
                // 微信小程序登录
                return this.wxAppletLogin(param);
            }
            default:{
                throw new SecurityException("登录方式错误");
            }
        }
    }

    /**
     * 用户名密码登录
     * @param param  授权请求参数
     * @return  返回用户授权信息
     */
    private UserAuthDTO usernameLogin(UserAuthParam param){
        Validator.getInstance().notBlank(param.getUsername(),"username")
                .notBlank(param.getSecretKey(),"password");
        //获取用户信息
        UserInfoDTO userInfo = userInfoDeal.queryByUsername(param.getUsername(),param.getUserType());
        if (Objects.isNull(userInfo)) {
            throw new SecurityException("用户名或密码错误");
        }
        if (!userInfo.getEnableFlag()) {
            throw new SecurityException("用户被禁用");
        }
        // 检查密码是否正确
        this.checkPassword(userInfo.getId(),param.getSecretKey());
        //返回授权信息
        return this.doAuth(userInfo);
    }

    /**
     * 用户名密码登录
     * @param param  授权请求参数
     * @return  返回用户授权信息
     */
    private UserAuthDTO mobilePasswordLogin(UserAuthParam param){
        Validator.getInstance().notBlank(param.getMobile(),"mobile")
                .notBlank(param.getSecretKey(),"password");
        //获取用户信息
        UserInfoDTO userInfo = userInfoDeal.queryByMobile(param.getMobile(),param.getUserType());
        if (Objects.isNull(userInfo)) {
            throw new SecurityException("手机号或密码错误");
        }
        if (!userInfo.getEnableFlag()) {
            throw new SecurityException("用户被禁用");
        }
        // 检查密码是否正确
        this.checkPassword(userInfo.getId(),param.getSecretKey());
        // 返回授权信息
        return this.doAuth(userInfo);
    }

    /**
     * 手机短信验证码登录
     * @param param 授权请求参数
     * @return  返回用户授权信息
     */
    private UserAuthDTO mobileSmsCodeLogin(UserAuthParam param){
        Validator.getInstance().notBlank(param.getMobile(),"mobile")
                .notBlank(param.getSecretKey(),"smsCode");
        //获取用户信息
        UserInfoDTO userInfo = userInfoDeal.queryByMobile(param.getMobile(),param.getUserType());
        if (Objects.isNull(userInfo)) {
            throw new SecurityException("手机号或密码错误");
        }
        if (!userInfo.getEnableFlag()) {
            throw new SecurityException("用户被禁用");
        }
        smsHelper.verifySmsCode(userInfo.getMobile(),param.getSecretKey());
        return this.doAuth(userInfo);
    }

    /**
     * 邮箱密码登录
     * @param param 授权请求参数
     * @return  返回用户授权信息
     */
    private UserAuthDTO emailPasswordLogin(UserAuthParam param){
        Validator.getInstance().notBlank(param.getEmail(),"email")
                .notBlank(param.getSecretKey(),"password");
        //获取用户信息
        UserInfoDTO userInfo = userInfoDeal.queryByEmail(param.getEmail(),param.getUserType());
        if (Objects.isNull(userInfo)) {
            throw new SecurityException("邮箱或密码错误");
        }
        if (!userInfo.getEnableFlag()) {
            throw new SecurityException("用户被禁用");
        }
        // 检查密码是否正确
        this.checkPassword(userInfo.getId(),param.getSecretKey());
        // 返回用户授权信息
        return this.doAuth(userInfo);
    }

    /**
     * 微信小程序登录
     * @param param 请求参数
     * @return  返回结果
     */
    private UserAuthDTO wxAppletLogin(UserAuthParam param){
        Validator.getInstance().notBlank(param.getCode(),"code");
        final WxMaService wxService = WxMaConfiguration.getMaService(appId);
        final WxMaUserService wxMaUserService = wxService.getUserService();
        WxMaJscode2SessionResult session;
        try {
            session = wxMaUserService.getSessionInfo(param.getCode());
        } catch (WxErrorException e) {
            throw new SecurityException("微信登陆失败");
        }
        UserLoginDTO userLogin = userLoginDeal.getBySecretKey(session.getOpenid(),LoginTypeEnum.WX_APPLET.getCode());
        //若userLogin不为空则获取用户信息
        if (Objects.nonNull(userLogin)) {
            UserInfoDTO userInfo = userInfoDeal.queryById(userLogin.getUserId());
            return this.doAuth(userInfo);
        }
        //若存在微信加密数据，则进行解密处理，获取微信手机号

        if (StringUtil.isNotBlank(param.getEncryptedData()) && StringUtil.isNotBlank(param.getIv()) && StringUtil.isNotBlank(param.getCode())) {
            WxMaPhoneNumberInfo phoneNoInfo = wxMaUserService.getPhoneNoInfo(session.getSessionKey(), param.getEncryptedData(), param.getIv());
            param.setMobile(phoneNoInfo.getPurePhoneNumber());
        }
        if (StringUtil.isBlank(param.getMobile())) {
            throw new SecurityException("微信小程序登录失败");
        }
        //根据手机号登录
        UserInfoDTO userInfo = userInfoDeal.queryByMobile(param.getMobile(),param.getUserType());
        if (Objects.nonNull(userInfo)) {
            userLogin = userLoginDeal.getByUserId(userInfo.getId(),LoginTypeEnum.WX_APPLET.getCode());
            if (Objects.isNull(userLogin)) {
                userLogin = new UserLoginDTO();
                userLogin.setUserId(userInfo.getId());
                userLogin.setLoginType(LoginTypeEnum.WX_APPLET.getCode());
            }
            userLogin.setUnionId(session.getUnionid());
            userLogin.setSecretKey(session.getOpenid());
            userLoginDeal.saveOrUpdate(userLogin);
            return this.doAuth(userInfo);
        }
        //若无用户信息，则创建用户信息
        userInfo = new UserInfoDTO();
        userInfo.setMobile(param.getMobile());
        userInfo.setUserType(param.getUserType());
        userInfo.setSource(UserSourceEnum.WX_APPLET.getCode());
        CreateUserModel model = new CreateUserModel();
        model.setUserInfo(userInfo);
        model.setLoginType(LoginTypeEnum.WX_APPLET.getCode());
        model.setSecretKey(session.getOpenid());
        model.setUnionId(session.getUnionid());
        Long userId = CreateUserExecutorRegister.findByType(UserTypeEnum.findByType(param.getUserType())).doExecute(model);
        userInfo.setId(userId);
        return this.doAuth(userInfo);
    }

    private String genUsername(){
        String uuid = UUID.randomUUID().toString();
        return uuid.substring(0,uuid.indexOf("-")) + RandomStringUtils.randomAlphanumeric(8);
    }
    /**
     * 检查密码
     * @param userId    用户id
     * @param password  用户输入的密码
     */
    private void checkPassword(Long userId,String password){
        //获取用户登录信息
        UserLoginDTO userLogin = userLoginDeal.getByUserId(userId,LoginTypeEnum.PASSWORD.getCode());
        if (Objects.isNull(userLogin) || !PasswordUtil.matches(password,userLogin.getSecretKey())) {
            throw new SecurityException("账号或密码错误");
        }
    }

    /**
     * 用户授权
     * @param userInfo  用户信息
     * @return  返回用户授权信息
     */
    private UserAuthDTO doAuth(UserInfoDTO userInfo){
        AuthUser authUser = new AuthUser();
        authUser.setUserId(userInfo.getId());
        authUser.setUserType(userInfo.getUserType());
        String token = tokenHelper.generateUserLoginToken(authUser);
        authUser.setToken(token);
        return ObjectUtils.copy(authUser,UserAuthDTO.class);
    }



}
