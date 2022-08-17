package com.sbx.app.system.service;

import com.sbx.app.system.dto.StsDTO;

/**
 * <p>说明：</p>
 *
 * @author Z.jc
 * @version 1.0.0
 * @since 2021/1/7
 */
public interface ISysOssService {

    /**
     * oss sts授权
     * @return  返回授权信息
     */
    StsDTO sts(String fileName);
}
