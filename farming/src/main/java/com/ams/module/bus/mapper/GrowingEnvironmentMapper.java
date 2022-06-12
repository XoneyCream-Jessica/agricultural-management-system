package com.ams.module.bus.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.ams.module.bus.entity.GrowingEnvironment;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 生长环境记录表 Mapper 接口
 * </p>
 *
 * @author Auto-generator
 * @since 2022-05-07
 */
public interface GrowingEnvironmentMapper extends BaseMapper<GrowingEnvironment> {

    List<GrowingEnvironment> findList(@Param(Constants.WRAPPER) QueryWrapper<GrowingEnvironment> qw);

    List<Map<String, Object>> findProcGrowEnvList();

    List<GrowingEnvironment> findHumitureListByProcId(String productId);
}
