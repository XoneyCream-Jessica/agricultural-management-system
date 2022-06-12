package com.ams.module.bus.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.ams.module.bus.entity.Nutrition;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 营养成分检测表 Mapper 接口
 * </p>
 *
 * @author Auto-generator
 * @since 2022-05-07
 */
public interface NutritionMapper extends BaseMapper<Nutrition> {

    List<Nutrition> findList(@Param(Constants.WRAPPER) QueryWrapper<Nutrition> qw);
}
