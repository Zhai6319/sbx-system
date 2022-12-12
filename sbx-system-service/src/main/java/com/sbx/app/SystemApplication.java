package com.sbx.app;

import com.sbx.core.cloud.annotation.CloudConfigure;
import com.sbx.core.launch.Application;
import com.sbx.core.redis.annotation.EnableRedis;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;

/**
 * <p>说明：</p>
 *
 * @author Z.jc
 * @version 1.0.0
 * @since 2021/6/26
 */
@EnableDubbo
@EnableRedis
@CloudConfigure
public class SystemApplication {

    public static void main(String[] args) {
        Application.run("sbx-system", SystemApplication.class,args);
    }
}
