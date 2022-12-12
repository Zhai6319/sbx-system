package com.sbx.app.system.api;


import com.sbx.app.system.dto.StsDTO;
import com.sbx.core.model.api.Response;

/**
 * <p>说明：</p>
 *
 * @author Z.jc
 * @version 1.0.0
 * @since 2021/1/7
 */
public interface ISysOssApi {

    /**
     * 授权
     * @return  返回授权数据
     */
    Response<StsDTO> sts(String fileName);

}
