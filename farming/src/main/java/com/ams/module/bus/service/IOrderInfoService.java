package com.ams.module.bus.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ams.module.bus.entity.OrderInfo;

import java.util.List;

/**
 * <p>
 * 订单表 服务类
 * </p>
 *
 * @author Auto-generator
 * @since 2022-05-07
 */
public interface IOrderInfoService extends IService<OrderInfo> {

    List<OrderInfo> findList(OrderInfo orderInfo);
}
