package com.ams.module.bus.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ams.module.bus.entity.OrderForm;
import com.ams.util.Pager;

/**
 * <p>
 * 订单表 服务类
 * </p>
 *
 * @author Auto-generator
 * @since 2022-05-07
 */
public interface IOrderFormService extends IService<OrderForm> {

    Pager<OrderForm>findPage(Pager<OrderForm> pager, OrderForm orderForm);

    void confirmGoods(String id);

    void confirmDelivery(String id);
}
