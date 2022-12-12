package com.sbx.app.order.service.deal;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sbx.app.order.dataobject.OrderInfo;
import com.sbx.app.order.dto.OrderInfoDTO;
import com.sbx.app.order.mapper.OrderInfoMapper;
import com.sbx.app.order.service.query.OrderInfoQuery;
import com.sbx.core.model.base.result.PageResult;
import com.sbx.core.model.enums.EResultCode;
import com.sbx.core.model.exception.CustomException;
import com.sbx.core.mybatis.base.service.impl.BaseServiceImpl;
import com.sbx.core.tool.util.ObjectUtils;
import com.sbx.core.tool.util.StringUtil;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 订单信息 数据处理
 * </p>
 *
 * @author Z.jc
 * @since 2022-06-30
 */
@Service
public class OrderInfoDeal extends ServiceImpl<OrderInfoMapper, OrderInfo> {


    /**
    * 条件分页查询数据
    * @param query 查询条件
    * @return      返回分页列表
    */
    public PageResult<OrderInfoDTO> queryByCondition(OrderInfoQuery query){
        Page<OrderInfo> page = new Page<>(query.getCurrent(),query.getSize());
        LambdaQueryWrapper<OrderInfo> wrapper = this.buildWrapper(query);
        Page<OrderInfo> doPage = super.page(page,wrapper);

        List<OrderInfoDTO> dataList = ObjectUtils.copyList(doPage.getRecords(), OrderInfoDTO.class);
        PageResult<OrderInfoDTO> pageResult = new PageResult<>();
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
    public OrderInfoDTO queryById(Long id){
        OrderInfo orderInfo = baseMapper.selectById(id);
        return ObjectUtils.copy(orderInfo,OrderInfoDTO.class);
    }

    /**
     * 创建订单信息
     * @param orderInfoDTO   订单信息
     * @return              返回创建ID
     */
    public Long create(OrderInfoDTO orderInfoDTO){
        OrderInfo orderInfo = ObjectUtils.copy(orderInfoDTO,OrderInfo.class);
        if (!super.save(orderInfo)) {
            throw new CustomException(EResultCode.FAILURE,"创建订单信息失败");
        }
        orderInfoDTO.setId(orderInfo.getId());
        return orderInfoDTO.getId();
    }

    /**
     * 修改订单信息
     * @param orderInfoDTO   订单信息
     */
    public void update(OrderInfoDTO orderInfoDTO){
        OrderInfo orderInfo = ObjectUtils.copy(orderInfoDTO,OrderInfo.class);
            orderInfo.setUpdateTime(null);
            if (!super.updateById(orderInfo)) {
            throw new CustomException(EResultCode.FAILURE,"修改订单信息失败");
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
    private LambdaQueryWrapper<OrderInfo> buildWrapper(OrderInfoQuery query){
        LambdaQueryWrapper<OrderInfo> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(StringUtil.isNotBlank(query.getOrderSn()),OrderInfo::getOrderSn,query.getOrderSn());
        return wrapper;
    }

}