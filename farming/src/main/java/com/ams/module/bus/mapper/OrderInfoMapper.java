package com.ams.module.bus.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.ams.module.bus.entity.OrderInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 订单表 Mapper 接口
 * </p>
 *
 * @author Auto-generator
 * @since 2022-05-07
 */
public interface OrderInfoMapper extends BaseMapper<OrderInfo> {

    List<OrderInfo> findList(@Param(Constants.WRAPPER) QueryWrapper<OrderInfo> orderInfoQueryWrapper);
}
