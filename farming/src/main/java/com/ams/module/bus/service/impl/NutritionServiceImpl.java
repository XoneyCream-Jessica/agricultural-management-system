package com.ams.module.bus.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ams.constant.BusConstant;
import com.ams.module.bus.entity.Nutrition;
import com.ams.module.bus.mapper.NutritionMapper;
import com.ams.module.bus.service.INutritionService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 营养成分检测表 服务实现类
 * </p>
 *
 * @author Auto-generator
 * @since 2022-05-07
 */
@Service
public class NutritionServiceImpl extends ServiceImpl<NutritionMapper, Nutrition> implements INutritionService {

    @Override
    public List<Nutrition> findList(Nutrition en) {
        QueryWrapper<Nutrition> qw=new QueryWrapper<>();
        if(StrUtil.isNotEmpty(en.getPeId())){
            qw.eq("tab.pe_id",en.getPeId());
        }
        qw.eq("tab.del_flag", BusConstant.DEL_FLAG_N);
        qw.orderByDesc("tab.create_date");
        return baseMapper.findList(qw);
    }
}
