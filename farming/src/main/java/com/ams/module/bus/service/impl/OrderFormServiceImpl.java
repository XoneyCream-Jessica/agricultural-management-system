package com.ams.module.bus.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ams.abnormal.AmsException;
import com.ams.constant.BusConstant;
import com.ams.module.bus.entity.OrderForm;
import com.ams.module.bus.mapper.OrderFormMapper;
import com.ams.module.bus.service.IOrderFormService;
import com.ams.util.Pager;
import com.ams.util.UserUtil;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单表 服务实现类
 * </p>
 *
 * @author Auto-generator
 * @since 2022-05-07
 */
@Service
public class OrderFormServiceImpl extends ServiceImpl<OrderFormMapper, OrderForm> implements IOrderFormService {

    @Override
    public Pager<OrderForm> findPage(Pager<OrderForm> pager, OrderForm orderForm) {
        QueryWrapper<OrderForm> qw=new QueryWrapper<>();
        if(orderForm.getIsMe()){
            qw.eq("create_user", UserUtil.getUserId());
        }
        if(StrUtil.isNotEmpty(orderForm.getId())){
            qw.eq("id",orderForm.getId());
        }
        if(StrUtil.isNotEmpty(orderForm.getContact())){
            qw.like("contact",orderForm.getContact());
        }
        return baseMapper.selectPage(pager,qw);
    }

    @Override
    public void confirmGoods(String id) {
        //查询出当前订单
        OrderForm form=baseMapper.selectById(id);
        //判断订单状态
        if(form.getState().equals("01")){
            throw new AmsException("订单还未发货！");
        }else if(form.getState().equals("02")){
            //改变订单状态
            form.setState("03");
            baseMapper.updateById(form);
        }else if(form.getState().equals("03")){
            throw new AmsException("订单已经收货！");
        }else{
            throw new AmsException(BusConstant.SYSTEM_ERROR);
        }

    }

    @Override
    public void confirmDelivery(String id) {
        //查询出当前订单
        OrderForm form=baseMapper.selectById(id);
        //判断订单状态
        if(form.getState().equals("01")){
            //改变订单状态
            form.setState("02");
            baseMapper.updateById(form);
        }else if(form.getState().equals("02")){
            throw new AmsException("订单已发货！");
        }else if(form.getState().equals("03")){
            throw new AmsException("订单已收货！");
        }else{
            throw new AmsException(BusConstant.SYSTEM_ERROR);
        }
    }
}
