package com.ams.module.sys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ams.module.sys.entity.SysRole;
import com.ams.module.sys.entity.SysUserRole;
import com.ams.module.sys.mapper.SysUserRoleMapper;
import com.ams.module.sys.service.ISysUserRoleService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Auto-generator
 * @since 2022-05-07
 */
@Service
public class SysUserRoleServiceImpl extends ServiceImpl<SysUserRoleMapper, SysUserRole> implements ISysUserRoleService {

    @Override
    public List<SysRole> findRoleByUserId(String id) {
        return baseMapper.findRoleByUserId(id);
    }
}
