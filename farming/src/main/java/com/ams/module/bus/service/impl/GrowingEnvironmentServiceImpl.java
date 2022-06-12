package com.ams.module.bus.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ams.constant.BusConstant;
import com.ams.module.bus.entity.GrowingEnvironment;
import com.ams.module.bus.mapper.GrowingEnvironmentMapper;
import com.ams.module.bus.service.IGrowingEnvironmentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 生长环境记录表 服务实现类
 * </p>
 *
 * @author Auto-generator
 * @since 2022-05-07
 */
@Service
public class GrowingEnvironmentServiceImpl extends ServiceImpl<GrowingEnvironmentMapper, GrowingEnvironment> implements IGrowingEnvironmentService {

    @Override
    public List<GrowingEnvironment> findList(GrowingEnvironment en) {
        QueryWrapper<GrowingEnvironment> qw = new QueryWrapper<>();
        if (StrUtil.isNotEmpty(en.getProductId())) {
            qw.eq("tab.product_id", en.getProductId());
        }
        qw.eq("tab.del_flag", BusConstant.DEL_FLAG_N);
        qw.orderByDesc("tab.create_date");
        return baseMapper.findList(qw);
    }

    @Override
    public List<Map<String, Object>> findProcGrowEnvList() {
        return baseMapper.findProcGrowEnvList();
    }

    @Override
    public GrowingEnvironment findLastGrowEnv(GrowingEnvironment en) {
        QueryWrapper<GrowingEnvironment> qw = new QueryWrapper<>();
        if (StrUtil.isNotEmpty(en.getProductId())) {
            qw.eq("product_id", en.getProductId());
        }
        qw.eq("del_flag", BusConstant.DEL_FLAG_N);
        qw.orderByDesc("collect_date");
        qw.last("LIMIT 1");
        return baseMapper.selectOne(qw);
    }

    @Override
    public List<GrowingEnvironment> findHumitureListByProcId(String productId) {
        return baseMapper.findHumitureListByProcId(productId);
    }


}
