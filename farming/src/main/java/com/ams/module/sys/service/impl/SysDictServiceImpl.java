package com.ams.module.sys.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ams.module.sys.entity.SysDict;
import com.ams.module.sys.mapper.SysDictMapper;
import com.ams.module.sys.service.ISysDictService;
import com.ams.util.Pager;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 字典表 服务实现类
 * </p>
 *
 * @author Auto-generator
 * @since 2022-05-07
 */
@Service
public class SysDictServiceImpl extends ServiceImpl<SysDictMapper, SysDict> implements ISysDictService {

    @Override
    public Pager<SysDict> findPage(Pager<SysDict> pager, SysDict sysDict) {
        QueryWrapper<SysDict> qw=new QueryWrapper<>();
        if(StrUtil.isNotEmpty(sysDict.getDictName())){
            qw.like("dict_name",sysDict.getDictName());
        }
        if(StrUtil.isNotEmpty(sysDict.getDictCode())){
            qw.like("dict_code",sysDict.getDictCode());
        }
        return baseMapper.selectPage(pager,qw);
    }
}
