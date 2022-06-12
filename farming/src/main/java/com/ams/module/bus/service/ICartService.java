package com.ams.module.bus.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ams.module.bus.entity.Cart;
import com.ams.module.bus.entity.OrderForm;
import com.ams.module.bus.vo.CartVo;

import java.util.List;

/**
 * <p>
 * 购物车表 服务类
 * </p>
 *
 * @author Auto-generator
 * @since 2021-06-04
 */
public interface ICartService extends IService<Cart> {

    List<CartVo> findList(Cart cart);

    void add(Cart cart);

    void empty();

    void buy(OrderForm orderForm);
}
