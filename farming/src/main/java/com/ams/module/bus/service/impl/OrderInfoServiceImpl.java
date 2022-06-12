package com.ams.module.bus.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ams.module.bus.entity.OrderInfo;
import com.ams.module.bus.mapper.OrderInfoMapper;
import com.ams.module.bus.service.IOrderInfoService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 订单表 服务实现类
 * </p>
 *
 * @author Auto-generator
 * @since 2022-05-07
 */
@Service
public class OrderInfoServiceImpl extends ServiceImpl<OrderInfoMapper, OrderInfo> implements IOrderInfoService {

    @Override
    public List<OrderInfo> findList(OrderInfo orderInfo) {
        return baseMapper.findList(
                new QueryWrapper<OrderInfo>()
                    .eq("tab.order_id",orderInfo.getOrderId())
                    .orderByDesc("tab.create_date")
        );
    }
}
