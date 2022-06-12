package com.ams.module.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ams.abnormal.AmsException;
import com.ams.module.sys.entity.SysMenu;
import com.ams.module.sys.entity.SysMenuRole;
import com.ams.module.sys.mapper.SysMenuMapper;
import com.ams.module.sys.mapper.SysMenuRoleMapper;
import com.ams.module.sys.service.ISysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 菜单表 服务实现类
 * </p>
 *
 * @author Auto-generator
 * @since 2022-05-07
 */
@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements ISysMenuService {
    @Autowired
    private SysMenuRoleMapper menuRoleMapper;

    @Override
    public List<SysMenu> findTreeByUid(String uid) {
        //查询当前用户所拥有的的菜单
        List<String> types=new ArrayList<>();
        types.add("0");
        types.add("1");
        List<SysMenu> sysMenus=baseMapper.findByUserId(uid).stream().filter(x->types.indexOf(x.getType())!=-1).collect(Collectors.toList());
        //构建数
        return buildTree(sysMenus,"0");
    }

    @Override

    public List<SysMenu> findMenuTree() {
        return buildTree(baseMapper.selectList(new QueryWrapper<SysMenu>().orderByAsc("sort")),"0");
    }

    @Override
    public List<SysMenuRole> findMenuRole(String rid) {
        //删除原来的所有菜单
        QueryWrapper<SysMenuRole> qw = new QueryWrapper<>();
        qw.eq("role_id", rid);
        return menuRoleMapper.selectList(qw);
    }

    @Override
    public void delById(String id) {
        //判断当前菜单是否可以删除
        if(baseMapper.selectCount(new QueryWrapper<SysMenu>().eq("pid",id))>0){
            throw new AmsException("当前菜单下还有子项不可删除！");
        }else{
            //删除
            baseMapper.deleteById(id);
        }

    }


    /**
     * 构建才菜单树
     * @return
     */
    private List<SysMenu> buildTree(List<SysMenu> menuList,String pid){
        List<SysMenu> children = new ArrayList<>();
        menuList.forEach(menu -> {
            if (menu.getPid().equals(pid)) {
                menu.setChildren(buildTree(menuList, menu.getId()));
                children.add(menu);
            }
        });
        return children;
    }




}
