package com.sbx.app.log.service.query;

import com.sbx.core.model.params.Query;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
* <p>
* 日志记录
* </p>
*
* @author Z.jc
* @since 2021-10-29
*/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class LogQuery extends Query {
    private static final long serialVersionUID = 1L;
    /**
     * 用户id
     */
    private Long userId;
    /**
     * 日志标题
     */
    private String logTitle;
    /**
     * 请求参数
     */
    private String params;
    /**
     * 日志内容
     */
    private String content;
    /**
     * 日志类型 0系统日志
     */
    private Integer logType;
    /**
     * 请求方法
     */
    private String method;
    /**
     * 方法类
     */
    private String methodClass;
    /**
     * 方法名称
     */
    private String methodName;
    /**
     * 请求ip
     */
    private String remoteIp;
    /**
     * 请求地址
     */
    private String requestUri;


}
