package com.sbx.app.applet.api;

import com.sbx.app.applet.dto.WebsiteAppEmpowerDTO;
import com.sbx.app.applet.params.WebsiteAppEmpowerParam;
import com.sbx.core.model.api.Response;
import com.sbx.core.model.base.result.PageResult;


/**
 * <p>
 * 域名应用授权 dubbo接口
 * </p>
 *
 * @author Z.jc
 * @since 2022-05-20
 */
public interface IWebsiteAppEmpowerApi {

    /**
     * 条件分页查询域名应用授权
     * @param param 查询条件
     * @return      返回域名应用授权分页列表
     */
    Response<PageResult<WebsiteAppEmpowerDTO>> queryByCondition(WebsiteAppEmpowerParam param);

    /**
     * 根据id查询域名应用授权
     * @param id 数据id
     * @return   返回域名应用授权
     */
    Response<WebsiteAppEmpowerDTO> queryDetailById(Long id);

    /**
     * 创建域名应用授权
     * @param websiteAppEmpowerDTO   域名应用授权数据
     * @return              返回创建数据id
     */
    Response<Long> create(WebsiteAppEmpowerDTO websiteAppEmpowerDTO);

    /**
     * 修改域名应用授权
     * @param websiteAppEmpowerDTO   域名应用授权数据
     * @return              返回结果
     */
    Response<Boolean> update(WebsiteAppEmpowerDTO websiteAppEmpowerDTO);

    /**
     * 根据id删除域名应用授权
     * @param id    数据id
     * @return      返回删除结果
     */
    Response<Boolean> delById(Long id);

}
