package com.ams.module.bus.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ams.module.bus.entity.SpotCheck;

import java.util.List;

/**
 * <p>
 * 产品抽查表 服务类
 * </p>
 *
 * @author Auto-generator
 * @since 2022-05-07
 */
public interface ISpotCheckService extends IService<SpotCheck> {

    List<SpotCheck> findList(SpotCheck spotCheck);
}
