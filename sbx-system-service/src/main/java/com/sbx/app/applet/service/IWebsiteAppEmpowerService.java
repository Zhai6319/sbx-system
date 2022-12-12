package com.sbx.app.applet.service;

import com.sbx.app.applet.dto.WebsiteAppEmpowerDTO;
import com.sbx.app.applet.params.WebsiteAppEmpowerParam;
import com.sbx.core.model.base.result.PageResult;


/**
 * <p>
 * 域名应用授权 服务类
 * </p>
 *
 * @author Z.jc
 * @since 2022-05-20
 */
public interface IWebsiteAppEmpowerService {

    /**
     * 分页查询域名应用授权
     * @param param 查询条件
     * @return      返回分页列表
     */
    PageResult<WebsiteAppEmpowerDTO> page(WebsiteAppEmpowerParam param);

   /**
    * 根据id获取域名应用授权详情
    * @param id    数据id
    * @return      返回域名应用授权详情
    */
   WebsiteAppEmpowerDTO detailById(Long id);

    /**
     * 创建域名应用授权
     * @param websiteAppEmpowerDTO   域名应用授权数据
     * @return              返回创建数据id
     */
    Long create(WebsiteAppEmpowerDTO websiteAppEmpowerDTO);

    /**
     * 修改域名应用授权
     * @param websiteAppEmpowerDTO   域名应用授权数据
     */
    void update(WebsiteAppEmpowerDTO websiteAppEmpowerDTO);

    /**
     * 根据id删除域名应用授权
     * @param id    数据id
     * @return      返回删除结果
     */
    Boolean delById(Long id);

}
