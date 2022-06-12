package com.ams.module.bus.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.ams.module.bus.entity.ProductExampleImg;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 产品实例图片表 Mapper 接口
 * </p>
 *
 * @author Auto-generator
 * @since 2022-05-07
 */
public interface ProductExampleImgMapper extends BaseMapper<ProductExampleImg> {

    List<ProductExampleImg> findList(@Param(Constants.WRAPPER) QueryWrapper<ProductExampleImg> qw);
}
