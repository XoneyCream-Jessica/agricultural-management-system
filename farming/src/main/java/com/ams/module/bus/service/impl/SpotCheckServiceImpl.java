package com.ams.module.bus.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ams.constant.BusConstant;
import com.ams.module.bus.entity.SpotCheck;
import com.ams.module.bus.mapper.SpotCheckMapper;
import com.ams.module.bus.service.ISpotCheckService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 产品抽查表 服务实现类
 * </p>
 *
 * @author Auto-generator
 * @since 2022-05-07
 */
@Service
public class SpotCheckServiceImpl extends ServiceImpl<SpotCheckMapper, SpotCheck> implements ISpotCheckService {

    @Override
    public List<SpotCheck> findList(SpotCheck en) {
        QueryWrapper<SpotCheck> qw=new QueryWrapper<>();
        if(StrUtil.isNotEmpty(en.getPeId())){
            qw.eq("pe_id",en.getPeId());
        }
        qw.eq("del_flag", BusConstant.DEL_FLAG_N);
        qw.orderByDesc("create_date");
        return baseMapper.selectList(qw);
    }
}
