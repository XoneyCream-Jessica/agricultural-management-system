package com.ams.module.bus.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ams.module.bus.entity.Nutrition;

import java.util.List;

/**
 * <p>
 * 营养成分检测表 服务类
 * </p>
 *
 * @author Auto-generator
 * @since 2022-05-07
 */
public interface INutritionService extends IService<Nutrition> {

    List<Nutrition> findList(Nutrition en);
}
