package com.sbx.app.user.service.executor.user.update;

import com.sbx.app.user.dto.UserInfoDTO;
import com.sbx.app.user.enums.UserTypeEnum;
import com.sbx.app.user.service.deal.UserInfoDeal;
import com.sbx.app.user.service.executor.user.update.model.UpdateUserModel;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * <p>说明：</p>
 *
 * @author Z.jc
 * @version 1.0.0
 * @since 2021/6/27
 */
@Component
public class UpdateUserExecutorForTour extends UpdateUserExecutor {
    public UpdateUserExecutorForTour(){
        UpdateUserExecutorRegister.register(UserTypeEnum.MALL,this);
    }

    @Resource
    private UserInfoDeal userInfoDeal;

    @Override
    protected void processor(UpdateUserModel model) {
        super.processor(model);
        UserInfoDTO userInfo = model.getUserInfo();
        userInfo.setMobile(null);
        userInfo.setEmail(null);
        userInfo.setUsername(null);
        userInfoDeal.update(userInfo);
    }
}
