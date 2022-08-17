package com.sbx.app.user.service.executor.user.update;

import com.google.common.collect.Maps;
import com.sbx.app.user.enums.UserTypeEnum;

import java.util.Map;

/**
 * <p>说明：</p>
 *
 * @author Z.jc
 * @version 1.0.0
 * @since 2021/6/27
 */
public class UpdateUserExecutorRegister {

    /**
     * 执行器容器
     */
    private final static Map<UserTypeEnum, UpdateUserExecutor> EXECUTOR_MAP = Maps.newHashMap();

    /**
     * 注册
     *
     * @param typeEnum 执行器类型
     * @param executor 执行器
     */
    public static void register(UserTypeEnum typeEnum, UpdateUserExecutor executor) {
        EXECUTOR_MAP.put(typeEnum, executor);
    }

    /**
     * 查找执行器
     *
     * @param typeEnum 类型枚举
     * @return 返回执行器
     */
    public static UpdateUserExecutor findByType(UserTypeEnum typeEnum) {
        return EXECUTOR_MAP.get(typeEnum);
    }
}
