package com.ams.module.bus.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ams.abnormal.AmsException;
import com.ams.module.bus.entity.Cart;
import com.ams.module.bus.entity.OrderForm;
import com.ams.module.bus.entity.OrderInfo;
import com.ams.module.bus.entity.ProductExample;
import com.ams.module.bus.mapper.CartMapper;
import com.ams.module.bus.mapper.OrderFormMapper;
import com.ams.module.bus.mapper.ProductExampleMapper;
import com.ams.module.bus.service.ICartService;
import com.ams.module.bus.service.IOrderInfoService;
import com.ams.module.bus.vo.CartVo;
import com.ams.util.CommMethod;
import com.ams.util.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 购物车表 服务实现类
 * </p>
 *
 * @author Auto-generator
 * @since 2022-05-07
 */
@Service
public class CartServiceImpl extends ServiceImpl<CartMapper, Cart> implements ICartService {
    @Autowired
    private ProductExampleMapper productExampleMapper;
    @Autowired
    private OrderFormMapper orderFormMapper;
    @Autowired
    private IOrderInfoService iOrderInfoService;

    @Override
    public List<CartVo> findList(Cart cart) {
        return baseMapper.findList(
                new QueryWrapper<Cart>()
                        .eq("tab.create_user", UserUtil.getUserId())
                        .orderByDesc("tab.create_date")
        );
    }

    @Override
    public void add(Cart cart) {
        //判断当前购物车是否该类商品
        Cart item=baseMapper.selectOne(
                new QueryWrapper<Cart>()
                    .eq("create_user",UserUtil.getUserId())
                    .eq("pe_id",cart.getPeId())
        );
        if(ObjectUtil.isEmpty(item)){
            //新增
            cart.setNumber(1);
            baseMapper.insert(cart);
        }else{
            //增加数量
            item.setNumber(item.getNumber()+1);
            baseMapper.updateById(item);
        }
    }

    @Override
    public void empty() {
        this.remove(
                new QueryWrapper<Cart>()
                    .eq("create_user",UserUtil.getUserId())
        );
    }

    @Override
    @Transactional
    public void buy(OrderForm orderForm) {
        //获取当前购物车的商品
        List<CartVo> list=baseMapper.findList(new QueryWrapper<Cart>().eq("create_user",UserUtil.getUserId()));
        //定义订单实例
        CommMethod.beanCreate(orderForm);
        //定义总价
        BigDecimal countMoney=new BigDecimal(0);
        //遍历购物车判断库存是否充足
        for (CartVo vo : list) {
            //查询出当前要购买的商品实例
            ProductExample example=productExampleMapper.selectById(vo.getExampleId());
            //判断库存大小
            if(example.getInventory()<vo.getNumber()){
                throw new AmsException("批号为："+example.getId()+"的商品库存不足-当前剩余库存："+example.getInventory());
            }
            //库存减少
            example.setInventory(example.getInventory()-vo.getNumber());
            productExampleMapper.updateById(example);
            //计算总价
            countMoney=countMoney.add(
                    example.getPrice().multiply(
                            new BigDecimal(vo.getNumber())
                    )
            );
        }

        //设置订单总价
        orderForm.setMoney(countMoney);
        orderForm.setState("01");
        //保存订单
        orderFormMapper.insert(orderForm);
        //定义订单详细集合
        List<OrderInfo> infos=list.stream().map(x->{
            OrderInfo info=new OrderInfo();
            CommMethod.beanCreate(info);
            info.setOrderId(orderForm.getId());
            info.setNumber(x.getNumber());
            info.setPeId(x.getExampleId());
            return info;
        }).collect(Collectors.toList());
        //批量保存
        iOrderInfoService.saveBatch(infos);

        //情况购物车
        this.empty();
    }
}
