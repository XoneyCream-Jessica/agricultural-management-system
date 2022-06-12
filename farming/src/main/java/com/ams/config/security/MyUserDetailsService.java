package com.ams.config.security;

import cn.hutool.core.util.ObjectUtil;
import com.ams.module.sys.entity.SysMenu;
import com.ams.module.sys.entity.SysRole;
import com.ams.module.sys.entity.SysUser;
import com.ams.module.sys.mapper.SysMenuMapper;
import com.ams.module.sys.service.ISysMenuService;
import com.ams.module.sys.service.ISysUserRoleService;
import com.ams.module.sys.service.ISysUserService;
import com.ams.util.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @ProjectName: AMS
 * @ClassName: MyUserDetailsService
 * @Author: chenxi
 * @Description: secuity获取用户接口
 * @Date: 2022/5/5 13:08
 */
@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private ISysUserService iSysUserService;
    @Autowired
    private SysMenuMapper sysMenuMapper;
    @Autowired
    private ISysMenuService iSysMenuService;
    @Autowired
    private ISysUserRoleService iSysUserRoleService;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser sysUser = iSysUserService.getUserInfoByUsername(username);
        if (sysUser == null) {      //用户不存在，认证失败
            throw new UsernameNotFoundException("用户名不存在");
        }

        //获取当前登录用户的角色
        List<SysRole> roles = iSysUserRoleService.findRoleByUserId(sysUser.getId());
        List<GrantedAuthority> auths = new ArrayList<>();

        //获取用户按钮权限和菜单权限
        List<SysMenu> sysMenuList=sysMenuMapper.findByUserId(sysUser.getId());

        //获取用户全部信息（菜单信息，权限信息）
        sysUser=iSysUserService.getUserByid(sysUser.getId());

        //设置角色
        auths.addAll(roles.stream().map(x -> {
            if(ObjectUtil.isNotEmpty(x)){
                return new SimpleGrantedAuthority(x.getCode());
            }else{
                return null;
            }
        }).collect(Collectors.toList()));

        //设置菜单权限
        auths.addAll(sysUser.getPermissions().stream().map(x->{
            if(ObjectUtil.isNotEmpty(x)){
                return new SimpleGrantedAuthority(x.getCode());
            }else{
                return null;
            }
        }).collect(Collectors.toList()));

        //设置session
        UserUtil.setUser(sysUser);
        return new User(sysUser.getUsername(), sysUser.getPassword(), auths);
    }
}