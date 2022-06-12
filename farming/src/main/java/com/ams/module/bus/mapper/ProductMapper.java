package com.ams.module.bus.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.ams.module.bus.entity.Product;
import com.ams.util.Pager;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Auto-generator
 * @since 2022-05-07
 */
public interface ProductMapper extends BaseMapper<Product> {

    Pager<Product> findPage(Pager<Product> pager,@Param(Constants.WRAPPER) QueryWrapper<Product> qw);

    Product findById(String productId);

    List<Product> findProcList();
}
