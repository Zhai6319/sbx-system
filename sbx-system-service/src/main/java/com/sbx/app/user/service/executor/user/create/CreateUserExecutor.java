package com.sbx.app.user.service.executor.user.create;

import com.sbx.app.user.dto.UserInfoDTO;
import com.sbx.app.user.service.executor.user.create.model.CreateUserModel;
import com.alibaba.fastjson.JSONObject;
import com.sbx.core.model.validator.Validator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


/**
 * <p>说明：</p>
 *
 * @author Z.jc
 * @version 1.0.0
 * @since 2021/6/27
 */
@Slf4j
@Component
public class CreateUserExecutor {

    public Long doExecute(CreateUserModel model){
        this.init(model);
        Long userId = this.processor(model);
        this.finish();
        return userId;
    }

    protected void init(CreateUserModel model){
        log.info("创建用户初始化数据处理");
        Validator.getInstance()
                .notNull(model.getUserInfo(),"userInfo")
                .notNull(model.getUserInfo().getUserType(),"userType")
                .notNull(model.getUserInfo().getSource(),"source");
    }

    protected Long processor(CreateUserModel model){
        log.info("创建用户处理:{}", JSONObject.toJSONString(model));
        return null;
    }

    protected void finish() {
        log.info("完成创建用户处理");
    }


}
