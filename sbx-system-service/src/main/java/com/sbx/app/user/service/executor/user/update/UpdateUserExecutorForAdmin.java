package com.sbx.app.user.service.executor.user.update;

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
import com.sbx.app.user.service.executor.user.update.model.UpdateUserModel;
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
public class UpdateUserExecutorForAdmin extends UpdateUserExecutor {
    public UpdateUserExecutorForAdmin(){
        UpdateUserExecutorRegister.register(UserTypeEnum.ADMIN,this);
    }

    @Resource
    private UserInfoDeal userInfoDeal;
    @Resource
    private UserLoginDeal userLoginDeal;
    @Resource
    private UserRoleDeal userRoleDeal;

    @Override
    protected void processor(UpdateUserModel model) {
        super.processor(model);
        UserInfoDTO userInfo = model.getUserInfo();
        //修改手机号检查手机号是否被用
        if (StringUtil.isNotBlank(userInfo.getMobile())) {
            UserInfoDTO oldUser = userInfoDeal.queryByMobile(userInfo.getMobile(),UserTypeEnum.ADMIN.getCode());
            if (Objects.nonNull(oldUser) && !Objects.equals(userInfo.getId(),oldUser.getId())) {
                throw new CustomException("手机号已存在");
            }
        }
        //修改用户名检查是否被用
        if (StringUtil.isNotBlank(userInfo.getUsername())) {
            UserInfoDTO oldUser = userInfoDeal.queryByUsername(userInfo.getUsername(),UserTypeEnum.ADMIN.getCode());
            if (Objects.nonNull(oldUser) && !Objects.equals(oldUser.getId(),userInfo.getId())) {
                throw new CustomException("用户名已存在");
            }
        }
        //邮箱验证
        if (StringUtil.isNotBlank(userInfo.getEmail())) {
            UserInfoDTO oldUser = userInfoDeal.queryByEmail(userInfo.getEmail(),UserTypeEnum.ADMIN.getCode());
            if (Objects.nonNull(oldUser) && !Objects.equals(userInfo.getId(),oldUser.getId())) {
                throw new CustomException("邮箱已存在");
            }
        }
        userInfoDeal.update(userInfo);
        userRoleDeal.delByUserId(userInfo.getId());
        userRoleDeal.batchCreateByUserId(model.getRoleIds(),userInfo.getId());
    }
}
