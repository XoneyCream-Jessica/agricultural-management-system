package com.ams.module.bus.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ams.module.bus.entity.GrowingEnvironment;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 生长环境记录表 服务类
 * </p>
 *
 * @author Auto-generator
 * @since 2022-05-07
 */
public interface IGrowingEnvironmentService extends IService<GrowingEnvironment> {

    List<GrowingEnvironment> findList(GrowingEnvironment en);

    List<Map<String, Object>> findProcGrowEnvList();

    GrowingEnvironment findLastGrowEnv(GrowingEnvironment en);

    List<GrowingEnvironment> findHumitureListByProcId(String productId);
}
