package com.sbx.app.user.service.executor.user.update;

import com.alibaba.fastjson.JSONObject;
import com.sbx.app.user.service.executor.user.create.model.CreateUserModel;
import com.sbx.app.user.service.executor.user.update.model.UpdateUserModel;
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
public class UpdateUserExecutor {

    public void doExecute(UpdateUserModel model){
        this.init(model);
        this.processor(model);
        this.finish();
    }

    protected void init(UpdateUserModel model){
        log.info("修改用户初始化数据处理");
        Validator.getInstance()
                .notNull(model.getUserInfo(),"userInfo")
                .notNull(model.getUserInfo().getId(),"id");
    }

    protected void processor(UpdateUserModel model){
        log.info("修改用户处理:{}", JSONObject.toJSONString(model));
    }

    protected void finish() {
        log.info("完成修改用户处理");
    }


}
