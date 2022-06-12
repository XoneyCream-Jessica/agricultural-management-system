package com.ams.module.bus.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.ams.module.bus.entity.Cart;
import com.ams.module.bus.vo.CartVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 购物车表 Mapper 接口
 * </p>
 *
 * @author Auto-generator
 * @since 2022-05-07
 */
public interface CartMapper extends BaseMapper<Cart> {

    List<CartVo> findList(@Param(Constants.WRAPPER) QueryWrapper<Cart> create_user);
}
