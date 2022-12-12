package com.sbx.app.user.service.executor.user.create;

import com.google.common.collect.Lists;
import com.sbx.app.user.dto.UserInfoDTO;
import com.sbx.app.user.dto.UserLoginDTO;
import com.sbx.app.user.dto.UserRoleDTO;
import com.sbx.app.user.enums.LoginTypeEnum;
import com.sbx.app.user.enums.UserTypeEnum;
import com.sbx.app.user.service.deal.UserInfoDeal;
import com.sbx.app.user.service.deal.UserLoginDeal;
import com.sbx.app.user.service.deal.UserRoleDeal;
import com.sbx.app.user.service.executor.user.create.model.CreateUserModel;
import com.sbx.common.auth.PasswordUtil;
import com.sbx.core.model.exception.CustomException;
import com.sbx.core.model.validator.Validator;
import com.sbx.core.tool.util.CollectionUtil;
import com.sbx.core.tool.util.StringUtil;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

/**
 * <p>说明：</p>
 *
 * @author Z.jc
 * @version 1.0.0
 * @since 2021/6/27
 */
@Component
public class CreateUserExecutorForAdmin extends CreateUserExecutor{
    public CreateUserExecutorForAdmin(){
        CreateUserExecutorRegister.register(UserTypeEnum.ADMIN,this);
    }

    @Resource
    private UserInfoDeal userInfoDeal;
    @Resource
    private UserLoginDeal userLoginDeal;
    @Resource
    private UserRoleDeal userRoleDeal;

    @Override
    protected Long processor(CreateUserModel model) {
        super.processor(model);
        UserInfoDTO userInfo = model.getUserInfo();
        Validator.getInstance()
                .notBlank(userInfo.getUsername(),"username")
                .notBlank(model.getPassword(),"password");

        UserInfoDTO oldUser = userInfoDeal.queryByUsername(userInfo.getUsername(),UserTypeEnum.ADMIN.getCode());
        if (Objects.nonNull(oldUser)) {
            throw new CustomException("用户名已存在");
        }
        if (StringUtil.isNotBlank(userInfo.getMobile())) {
            oldUser = userInfoDeal.queryByMobile(userInfo.getMobile(),UserTypeEnum.ADMIN.getCode());
            if (Objects.nonNull(oldUser)) {
                throw new CustomException("手机号已存在");
            }
        }
        if (StringUtil.isNotBlank(userInfo.getEmail())) {
            oldUser = userInfoDeal.queryByEmail(userInfo.getEmail(),UserTypeEnum.ADMIN.getCode());
            if (Objects.nonNull(oldUser)) {
                throw new CustomException("邮箱已存在");
            }
        }
        Long userId = userInfoDeal.create(userInfo);
        UserLoginDTO userLogin = new UserLoginDTO();
        userLogin.setUserId(userId);
        userLogin.setLoginType(LoginTypeEnum.PASSWORD.getCode());
        userLogin.setSecretKey(PasswordUtil.encrypt(model.getPassword()));
        userLoginDeal.create(userLogin);
        userRoleDeal.batchCreateByUserId(model.getRoleIds(),userId);
        return userId;
    }
}
