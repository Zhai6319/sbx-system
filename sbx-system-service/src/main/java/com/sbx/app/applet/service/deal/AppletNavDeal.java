package com.sbx.app.applet.service.deal;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sbx.app.applet.dataobject.AppletNav;
import com.sbx.app.applet.dto.AppletNavDTO;
import com.sbx.app.applet.mapper.AppletNavMapper;
import com.sbx.app.applet.service.query.AppletNavQuery;
import com.sbx.core.model.base.result.PageResult;
import com.sbx.core.model.enums.EResultCode;
import com.sbx.core.model.exception.CustomException;
import com.sbx.core.mybatis.base.service.impl.BaseServiceImpl;
import com.sbx.core.tool.util.ObjectUtils;
import com.sbx.core.tool.util.StringUtil;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * <p>
 * 小程序菜单栏 数据处理
 * </p>
 *
 * @author Z.jc
 * @since 2022-03-05
 */
@Service
public class AppletNavDeal extends BaseServiceImpl<AppletNavMapper, AppletNav>  {


    /**
    * 条件分页查询数据
    * @param query 查询条件
    * @return      返回分页列表
    */
    public PageResult<AppletNavDTO> queryByCondition(AppletNavQuery query){
        Page<AppletNav> page = new Page<>(query.getCurrent(),query.getSize());
        LambdaQueryWrapper<AppletNav> wrapper = this.buildWrapper(query);
        wrapper.orderByAsc(AppletNav::getSort);
        Page<AppletNav> doPage = super.page(page,wrapper);

        List<AppletNavDTO> dataList = ObjectUtils.copyList(doPage.getRecords(), AppletNavDTO.class);
        PageResult<AppletNavDTO> pageResult = new PageResult<>();
        pageResult.setRecords(dataList);
        pageResult.setTotal(doPage.getTotal());
        pageResult.setSize(query.getSize());
        pageResult.setPages(doPage.getPages());
        pageResult.setCurrent(query.getCurrent());
        pageResult.setRecords(dataList);
        return pageResult;
    }

    /**
    * 根据id查询数据
    * @param id    数据id
    * @return      返回数据
    */
    public AppletNavDTO queryById(Long id){
        AppletNav appletNav = baseMapper.selectById(id);
        return ObjectUtils.copy(appletNav,AppletNavDTO.class);
    }

    /**
     * 创建小程序菜单栏
     * @param appletNavDTO   小程序菜单栏
     * @return              返回创建ID
     */
    public Long create(AppletNavDTO appletNavDTO){
        AppletNav appletNav = ObjectUtils.copy(appletNavDTO,AppletNav.class);
        appletNav.setSort(baseMapper.getSortValue());
        if (!super.save(appletNav)) {
            throw new CustomException(EResultCode.FAILURE,"创建小程序菜单栏失败");
        }
        appletNavDTO.setId(appletNav.getId());
        return appletNavDTO.getId();
    }

    /**
     * 修改小程序菜单栏
     * @param appletNavDTO   小程序菜单栏
     */
    public void update(AppletNavDTO appletNavDTO){
        AppletNav appletNav = ObjectUtils.copy(appletNavDTO,AppletNav.class);
            appletNav.setUpdateTime(null);
            if (!super.updateById(appletNav)) {
            throw new CustomException(EResultCode.FAILURE,"修改小程序菜单栏失败");
        }
    }

    /**
     * 根据ID删除数据
     * @param id    数据ID
     * @return      返回删除结果
     */
    public Boolean delById(Long id){
        return super.removeById(id);
    }

   /**
    * 构建查询条件
    * @param query    查询条件
    * @return      构建sql包装
    */
    private LambdaQueryWrapper<AppletNav> buildWrapper(AppletNavQuery query){
        LambdaQueryWrapper<AppletNav> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(StringUtil.isNotBlank(query.getNavName()),AppletNav::getNavName,query.getNavName());
        wrapper.eq(StringUtil.isNotBlank(query.getIcon()),AppletNav::getIcon,query.getIcon());
        wrapper.eq(StringUtil.isNotBlank(query.getSelectedIcon()),AppletNav::getSelectedIcon,query.getSelectedIcon());
        wrapper.eq(StringUtil.isNotBlank(query.getPagePath()),AppletNav::getPagePath,query.getPagePath());
        wrapper.eq(Objects.nonNull(query.getBizType()),AppletNav::getBizType,query.getBizType());
        return wrapper;
    }

}