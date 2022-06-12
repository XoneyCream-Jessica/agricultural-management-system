package com.ams.module.sys.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ams.module.sys.entity.SysDictItem;
import com.ams.module.sys.mapper.SysDictItemMapper;
import com.ams.module.sys.service.ISysDictItemService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 字典项表 服务实现类
 * </p>
 *
 * @author Auto-generator
 * @since 2022-05-07
 */
@Service
public class SysDictItemServiceImpl extends ServiceImpl<SysDictItemMapper, SysDictItem> implements ISysDictItemService {

    @Override
    public List<SysDictItem> findList(SysDictItem en) {
        QueryWrapper<SysDictItem> qw=new QueryWrapper<>();
        if(StrUtil.isNotEmpty(en.getDictId())){
            qw.eq("dict_id",en.getDictId());
        }
        qw.orderByAsc("sort");
        return baseMapper.selectList(qw);
    }

    @Override
    public List<Map<String, String>> findByDictCode(String code) {
        List<Map<String, String>> maps= baseMapper.findByDictCode(code).stream().map(x->{
            Map<String,String> map=new HashMap<>();
            map.put("label",x.getLabel());
            map.put("value",x.getVal());
            return map;
        }).collect(Collectors.toList());
        return maps;
    }
}
