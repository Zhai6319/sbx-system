package com.sbx.app.system.service.impl;

import com.aliyun.oss.common.utils.DateUtil;
import com.aliyun.oss.model.PolicyConditions;
import com.aliyuncs.auth.sts.AssumeRoleResponse;
import com.sbx.app.system.dto.StsDTO;
import com.sbx.app.system.service.ISysOssService;
import com.sbx.core.model.exception.CustomException;
import com.sbx.core.oss.component.AliOssHelper;
import com.sbx.core.oss.model.Signature;
import com.sbx.core.tool.util.ObjectUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.ParseException;

/**
 * <p>说明：</p>
 *
 * @author Z.jc
 * @version 1.0.0
 * @since 2021/1/7
 */
@Slf4j
@Service
public class SysOssServiceImpl implements ISysOssService {

    @Resource
    private AliOssHelper aliOssHelper;

    @Value("${sbx.env}")
    private String env;


    @Override
    public StsDTO sts(String fileName){
        AssumeRoleResponse.Credentials sts = aliOssHelper.sts(1 * 60 * 60);
        StsDTO stsDTO = ObjectUtils.copy(sts,StsDTO.class);
        PolicyConditions policyConditions = new PolicyConditions();
        policyConditions.addConditionItem(PolicyConditions.COND_CONTENT_LENGTH_RANGE, 0, 1048576000);
        String policyText = null;
        try {
            policyText = aliOssHelper.generatePostPolicy(DateUtil.parseIso8601Date(stsDTO.getExpiration()) , policyConditions);
        } catch (ParseException e) {
            log.error("获取签名失败：",e);
            throw new CustomException("获取签名异常");
        }

        Signature signature = aliOssHelper.computeSignature(policyText,stsDTO.getAccessKeySecret());
        stsDTO.setSignature(signature.getSignature());
        stsDTO.setPolicyText(signature.getEncodedPolicy());
        stsDTO.setObjectName(env + "/" + aliOssHelper.getKey(fileName));
        return stsDTO;
    }


}
