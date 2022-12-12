package com.sbx.app.user.service.executor.user.create;


import com.sbx.app.user.dto.UserInfoDTO;
import com.sbx.app.user.dto.UserLoginDTO;
import com.sbx.app.user.enums.LoginTypeEnum;
import com.sbx.app.user.enums.UserTypeEnum;
import com.sbx.app.user.service.deal.UserInfoDeal;
import com.sbx.app.user.service.deal.UserLoginDeal;
import com.sbx.app.user.service.executor.user.create.model.CreateUserModel;
import com.sbx.common.auth.PasswordUtil;
import com.sbx.core.model.exception.CustomException;
import com.sbx.core.model.validator.Validator;
import com.sbx.core.tool.util.TxtUtil;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * <p>说明：</p>
 *
 * @author Z.jc
 * @version 1.0.0
 * @since 2021/6/27
 */
@Component
public class CreateUserExecutorForTour extends CreateUserExecutor{
    public CreateUserExecutorForTour(){
        CreateUserExecutorRegister.register(UserTypeEnum.MALL,this);
    }

    @Resource
    private UserInfoDeal userInfoDeal;
    @Resource
    private UserLoginDeal userLoginDeal;
//    @Resource
//    private AccountRepository accountRepository;

    @Override
    protected Long processor(CreateUserModel model) {
        super.processor(model);
        UserInfoDTO userInfo = model.getUserInfo();
        Validator.getInstance()
                .notBlank(userInfo.getMobile(),"mobile");

        UserInfoDTO oldUser = userInfoDeal.queryByMobile(userInfo.getMobile(),UserTypeEnum.MALL.getCode());
        if (Objects.nonNull(oldUser)) {
            throw new CustomException("手机号已存在");
        }
        userInfo.setUsername(TxtUtil.genLetter(userInfo.getMobile()));
        Long userId = userInfoDeal.create(userInfo);
        UserLoginDTO userLogin = new UserLoginDTO();
        userLogin.setUserId(userId);
        userLogin.setLoginType(model.getLoginType());
        userLogin.setUnionId(model.getUnionId());
        LoginTypeEnum loginType = LoginTypeEnum.getByCode(model.getLoginType());
        if (Objects.isNull(loginType)) {
            throw new CustomException("登录类型错误");
        }
        switch (loginType) {
            case PASSWORD:{
                userLogin.setSecretKey(PasswordUtil.encrypt(model.getPassword()));
                break;
            }
            case WX_APPLET:{
                userLogin.setSecretKey(model.getSecretKey());
                break;
            }
            default:{
                break;
            }
        }
        userLoginDeal.create(userLogin);
//        //创建用户账户
//        AccountDTO account = new AccountDTO();
//        account.setUserId(userId);
//        account.setAccountType(UserTypeEnum.MALL.getCode());
//        account.setAvailableBalance(0L);
//        account.setFrozenBalance(0L);
//        account.setTotalWithdraw(0L);
//        account.setPointsBalance(0);
//        account.setRewardBalance(0L);
//        accountRepository.createAccount(account);
        return userInfo.getId();
    }
}
