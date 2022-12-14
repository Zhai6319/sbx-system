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
 * ???????????? ???????????????
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
            throw new SecurityException("??????????????????");
        }
        switch (loginMethod) {
            case USERNAME:{
                //?????????????????????
                return this.usernameLogin(param);
            }
            case MOBILE_PASSWORD:{
                //?????????????????????
                return this.mobilePasswordLogin(param);
            }
            case MOBILE_SMS_CODE:{
                // ????????????????????????
                return this.mobileSmsCodeLogin(param);
            }
            case EMAIL_PASSWORD:{
                // ??????????????????
                return this.emailPasswordLogin(param);
            }
            case WX_APPLET:{
                // ?????????????????????
                return this.wxAppletLogin(param);
            }
            default:{
                throw new SecurityException("??????????????????");
            }
        }
    }

    /**
     * ?????????????????????
     * @param param  ??????????????????
     * @return  ????????????????????????
     */
    private UserAuthDTO usernameLogin(UserAuthParam param){
        Validator.getInstance().notBlank(param.getUsername(),"username")
                .notBlank(param.getSecretKey(),"password");
        //??????????????????
        UserInfoDTO userInfo = userInfoDeal.queryByUsername(param.getUsername(),param.getUserType());
        if (Objects.isNull(userInfo)) {
            throw new SecurityException("????????????????????????");
        }
        if (!userInfo.getEnableFlag()) {
            throw new SecurityException("???????????????");
        }
        // ????????????????????????
        this.checkPassword(userInfo.getId(),param.getSecretKey());
        //??????????????????
        return this.doAuth(userInfo);
    }

    /**
     * ?????????????????????
     * @param param  ??????????????????
     * @return  ????????????????????????
     */
    private UserAuthDTO mobilePasswordLogin(UserAuthParam param){
        Validator.getInstance().notBlank(param.getMobile(),"mobile")
                .notBlank(param.getSecretKey(),"password");
        //??????????????????
        UserInfoDTO userInfo = userInfoDeal.queryByMobile(param.getMobile(),param.getUserType());
        if (Objects.isNull(userInfo)) {
            throw new SecurityException("????????????????????????");
        }
        if (!userInfo.getEnableFlag()) {
            throw new SecurityException("???????????????");
        }
        // ????????????????????????
        this.checkPassword(userInfo.getId(),param.getSecretKey());
        // ??????????????????
        return this.doAuth(userInfo);
    }

    /**
     * ???????????????????????????
     * @param param ??????????????????
     * @return  ????????????????????????
     */
    private UserAuthDTO mobileSmsCodeLogin(UserAuthParam param){
        Validator.getInstance().notBlank(param.getMobile(),"mobile")
                .notBlank(param.getSecretKey(),"smsCode");
        //??????????????????
        UserInfoDTO userInfo = userInfoDeal.queryByMobile(param.getMobile(),param.getUserType());
        if (Objects.isNull(userInfo)) {
            throw new SecurityException("????????????????????????");
        }
        if (!userInfo.getEnableFlag()) {
            throw new SecurityException("???????????????");
        }
        smsHelper.verifySmsCode(userInfo.getMobile(),param.getSecretKey());
        return this.doAuth(userInfo);
    }

    /**
     * ??????????????????
     * @param param ??????????????????
     * @return  ????????????????????????
     */
    private UserAuthDTO emailPasswordLogin(UserAuthParam param){
        Validator.getInstance().notBlank(param.getEmail(),"email")
                .notBlank(param.getSecretKey(),"password");
        //??????????????????
        UserInfoDTO userInfo = userInfoDeal.queryByEmail(param.getEmail(),param.getUserType());
        if (Objects.isNull(userInfo)) {
            throw new SecurityException("?????????????????????");
        }
        if (!userInfo.getEnableFlag()) {
            throw new SecurityException("???????????????");
        }
        // ????????????????????????
        this.checkPassword(userInfo.getId(),param.getSecretKey());
        // ????????????????????????
        return this.doAuth(userInfo);
    }

    /**
     * ?????????????????????
     * @param param ????????????
     * @return  ????????????
     */
    private UserAuthDTO wxAppletLogin(UserAuthParam param){
        Validator.getInstance().notBlank(param.getCode(),"code");
        final WxMaService wxService = WxMaConfiguration.getMaService(appId);
        final WxMaUserService wxMaUserService = wxService.getUserService();
        WxMaJscode2SessionResult session;
        try {
            session = wxMaUserService.getSessionInfo(param.getCode());
        } catch (WxErrorException e) {
            throw new SecurityException("??????????????????");
        }
        UserLoginDTO userLogin = userLoginDeal.getBySecretKey(session.getOpenid(),LoginTypeEnum.WX_APPLET.getCode());
        //???userLogin??????????????????????????????
        if (Objects.nonNull(userLogin)) {
            UserInfoDTO userInfo = userInfoDeal.queryById(userLogin.getUserId());
            return this.doAuth(userInfo);
        }
        //???????????????????????????????????????????????????????????????????????????

        if (StringUtil.isNotBlank(param.getEncryptedData()) && StringUtil.isNotBlank(param.getIv()) && StringUtil.isNotBlank(param.getCode())) {
            WxMaPhoneNumberInfo phoneNoInfo = wxMaUserService.getPhoneNoInfo(session.getSessionKey(), param.getEncryptedData(), param.getIv());
            param.setMobile(phoneNoInfo.getPurePhoneNumber());
        }
        if (StringUtil.isBlank(param.getMobile())) {
            throw new SecurityException("???????????????????????????");
        }
        //?????????????????????
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
        //??????????????????????????????????????????
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
     * ????????????
     * @param userId    ??????id
     * @param password  ?????????????????????
     */
    private void checkPassword(Long userId,String password){
        //????????????????????????
        UserLoginDTO userLogin = userLoginDeal.getByUserId(userId,LoginTypeEnum.PASSWORD.getCode());
        if (Objects.isNull(userLogin) || !PasswordUtil.matches(password,userLogin.getSecretKey())) {
            throw new SecurityException("?????????????????????");
        }
    }

    /**
     * ????????????
     * @param userInfo  ????????????
     * @return  ????????????????????????
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
