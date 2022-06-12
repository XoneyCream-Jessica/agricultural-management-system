package com.ams.module.sys.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ams.module.sys.entity.SysMenuRole;
import com.ams.module.sys.entity.SysRole;
import com.ams.module.sys.mapper.SysRoleMapper;
import com.ams.module.sys.service.ISysMenuRoleService;
import com.ams.module.sys.service.ISysRoleService;
import com.ams.util.Pager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements ISysRoleService {
    @Autowired
    private ISysMenuRoleService iMenuRoleService;

    @Override
    public Pager<SysRole> findPage(Pager<SysRole> pager, SysRole en) {
        QueryWrapper<SysRole> qw=new QueryWrapper<>();
        if(StrUtil.isNotEmpty(en.getCode())){
            qw.like("code",en.getCode());
        }
        if(StrUtil.isNotEmpty(en.getName())){
            qw.like("name",en.getName());
        }
        return baseMapper.selectPage(pager,qw);
    }

    @Override
    public List<SysRole> findByUserId(String userId) {
        return baseMapper.findByUserId(userId);
    }

    @Override
    @Transactional
    public void setMenuByRole(SysRole sysRole) {
        //删除原来的所有菜单
        iMenuRoleService.remove(new QueryWrapper<SysMenuRole>().eq("role_id", sysRole.getId()));
        List<SysMenuRole> menuRoles=new ArrayList<>();;
        //录入新的菜单
        sysRole.getMenus().forEach(x -> {
            SysMenuRole menuRole = new SysMenuRole();
            menuRole.setRoleId(sysRole.getId());
            menuRole.setMenuId(x.getId());
            menuRoles.add(menuRole);
        });
        iMenuRoleService.saveBatch(menuRoles);
    }

    @Override
    public List<SysRole> findList(SysRole en) {
        QueryWrapper<SysRole> qw=new QueryWrapper<>();
        if(StrUtil.isNotEmpty(en.getCode())){
            qw.like("code",en.getCode());
        }
        if(StrUtil.isNotEmpty(en.getName())){
            qw.like("name",en.getName());
        }
        return baseMapper.selectList(qw);
    }


}
