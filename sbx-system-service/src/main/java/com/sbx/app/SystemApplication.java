package com.sbx.app;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import com.baomidou.mybatisplus.autoconfigure.MybatisPlusAutoConfiguration;
import com.sbx.core.cloud.annotation.CloudConfigure;
import com.sbx.core.launch.Application;
import com.sbx.core.redis.annotation.EnableRedis;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.apache.shardingsphere.spring.boot.ShardingSphereAutoConfiguration;
import org.springframework.context.annotation.Import;

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
@Import({
        ShardingSphereAutoConfiguration.class,
        DruidDataSourceAutoConfigure.class,
        MybatisPlusAutoConfiguration.class
})
public class SystemApplication {

    public static void main(String[] args) {
        Application.run("sbx-system", SystemApplication.class,args);
    }
}
