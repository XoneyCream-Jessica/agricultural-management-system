package com.ams.module.bus.mapper;

import com.ams.module.bus.entity.Cart;
import com.ams.module.bus.vo.CartVo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.ams.module.bus.entity.ProductExample;
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
public interface ProductExampleMapper extends BaseMapper<ProductExample> {

    Pager<ProductExample> findPage(Pager<ProductExample> pager,@Param(Constants.WRAPPER)  QueryWrapper<ProductExample> qw);

    ProductExample findById(String id);

    List<ProductExample> findTitlePriceList();

    String getProductIdByPeId(String peId);
}
