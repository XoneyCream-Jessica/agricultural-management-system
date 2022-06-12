package com.ams.module.sys.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ams.abnormal.AmsException;
import com.ams.module.sys.dto.PassWordDto;
import com.ams.module.sys.dto.RegisteredDto;
import com.ams.module.sys.entity.SysMenu;
import com.ams.module.sys.entity.SysRole;
import com.ams.module.sys.entity.SysUser;
import com.ams.module.sys.entity.SysUserRole;
import com.ams.module.sys.mapper.SysMenuMapper;
import com.ams.module.sys.mapper.SysUserMapper;
import com.ams.module.sys.mapper.SysUserRoleMapper;
import com.ams.module.sys.service.ISysMenuService;
import com.ams.module.sys.service.ISysRoleService;
import com.ams.module.sys.service.ISysUserRoleService;
import com.ams.module.sys.service.ISysUserService;
import com.ams.util.Pager;
import com.ams.util.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Auto-generator
 * @since 2022-05-07
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {
    @Autowired
    private SysMenuMapper sysMenuMapper;
    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;
    @Autowired
    private ISysUserRoleService iSysUserRoleService;
    @Autowired
    private ISysRoleService iSysRoleService;
    @Autowired
    private ISysMenuService iSysMenuService;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Value("${password}")
    private String password;


    @Override
    public SysUser getUserInfoByUsername(String username) {
        QueryWrapper<SysUser> qw = new QueryWrapper<>();
        qw.eq("username", username);
        return baseMapper.selectOne(qw);
    }

    @Override
    public SysUser getUserByid(String id) {
        SysUser sysUser = baseMapper.selectById(id);
        //获取用户按钮权限和菜单权限
        List<SysMenu> sysMenuList = sysMenuMapper.findByUserId(sysUser.getId());
        //设置用户按钮权限
        sysUser.setPermissions(sysMenuList.stream().filter(x -> x.getType().equals("2")).collect(Collectors.toList()));
        //设置用户菜单权限
        sysUser.setSysMenus(iSysMenuService.findTreeByUid(sysUser.getId()));
        return sysUser;
    }

    @Override
    public SysUser getLoginUser(Principal principal) {
        //判断session里面是否有用户信息
        SysUser sysUser=UserUtil.getUser();
        if(sysUser==null){
            //重新获取用户信息
            sysUser=this.getUserInfoByUsername(principal.getName());
            sysUser=this.getUserByid(sysUser.getId());
            //设置session
            UserUtil.setUser(sysUser);
        }
        return sysUser;
    }

    @Override
    public Pager<SysUser> findPage(Pager<SysUser> pager, SysUser en) {
        QueryWrapper<SysUser> qw=new QueryWrapper<>();
        if(StrUtil.isNotEmpty(en.getUsername())){
            qw.like("tab.username",en.getUsername());
        }
        if(StrUtil.isNotEmpty(en.getName())){
            qw.like("tab.name",en.getName());
        }
        if(StrUtil.isNotEmpty(en.getRoleNames())){
            qw.like("tab.role_names",en.getRoleNames());
        }
        return baseMapper.findPage(pager,qw);
    }

    @Override
    public void saveObj(SysUser sysUser) {
        //判断当前用户是否存在
        if(this.getUserInfoByUsername(sysUser.getUsername())!=null){
            throw new AmsException("用户已经存在！");
        }
        sysUser.setCreateDate(LocalDateTime.now());
        sysUser.setCreateUser(UserUtil.getUserId());
        sysUser.setPassword( passwordEncoder.encode(password));
        this.save(sysUser);
    }

    @Override
    public void resetPassword(String id) {
        //获取当前用户
        SysUser sysUser=baseMapper.selectById(id);
        sysUser.setPassword(passwordEncoder.encode(password));
        baseMapper.updateById(sysUser);
    }

    @Override
    @Transactional
    public void setRoles(SysUser sysUser) {
        //删除用户原本的角色
        sysUserRoleMapper.delete(new QueryWrapper<SysUserRole>().eq("user_id",sysUser.getId()));
        //增加新的角色
        iSysUserRoleService.saveBatch(sysUser.getRoles().stream().map(x->{
            SysUserRole item=new SysUserRole();
            item.setUserId(sysUser.getId());
            item.setRoleId(x);
            return item;
        }).collect(Collectors.toList()));
    }

    @Override
    public void updatePassword(PassWordDto passWordDto) {
        //判断两次密码是否正确
        if(!passWordDto.getNewPass().equals(passWordDto.getConfirmPass())){
            throw new AmsException("两次密码不相同请重新输入！");
        }
        //查询出当前用户信息
        SysUser sysUser=UserUtil.getUser();
        //判断旧的密码是否相同
        if(!passwordEncoder.matches(passWordDto.getOdlPass(),sysUser.getPassword())){
            throw new AmsException("旧的密码不正确！");
        }

        //修改密码
        SysUser user=new SysUser();
        user.setId(sysUser.getId());
        user.setPassword(passwordEncoder.encode(passWordDto.getNewPass()));
        baseMapper.updateById(user);

    }

    @Override
    public void registered(RegisteredDto dto) {
        //注册
        //判断当前用户名是否存在用户
        if(ObjectUtil.isNotEmpty(this.getUserInfoByUsername(dto.getUsername()))){
            throw new AmsException("用户已经存在!");
        }
        //判断两次密码是否相同
        if(!dto.getPassword().equals(dto.getConfirmPassword())){
            throw new AmsException("两次密码不相同请重新输入！");
        }
        SysUser sysUser=new SysUser();
        //注册
        sysUser.setCreateDate(LocalDateTime.now());
        sysUser.setUsername(dto.getUsername());
        sysUser.setPassword(passwordEncoder.encode(dto.getPassword()));
        sysUser.setName(dto.getUsername());
        baseMapper.insert(sysUser);
        //设置权限
        //查询出普通用户权限
        SysRole sysRole=iSysRoleService.getOne(
                new QueryWrapper<SysRole>()
                    .eq("code","USER")
        );
        //设置角色
        SysUserRole sysUserRole=new SysUserRole();
        sysUserRole.setRoleId(sysRole.getId());
        sysUserRole.setUserId(sysUser.getId());
        iSysUserRoleService.save(sysUserRole);
    }

}
