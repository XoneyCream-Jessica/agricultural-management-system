package com.ams.module.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ams.module.sys.dto.PassWordDto;
import com.ams.module.sys.dto.RegisteredDto;
import com.ams.module.sys.entity.SysUser;
import com.ams.util.Pager;

import java.security.Principal;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Auto-generator
 * @since 2022-05-08
 */
public interface ISysUserService extends IService<SysUser> {
    /*
     * @param username:用户名
     * @Description: TODO(通过用户名获取用户)
     * @date 2021/5/14 20:58
     * @returns com.ht.module.sys.entity.SysUser
     */
    SysUser getUserInfoByUsername(String username);

    /*
     * @param id:用户id
     * @Description: TODO(通过id获取用户信息)
     * @date 2021/5/14 20:58
     * @returns com.ht.module.sys.entity.SysUser
     */
    SysUser getUserByid(String id);


    /*
     * @param principal
     * @Description: TODO(获取登录用户)
     * @date 2021/5/14 21:04
     * @returns com.ht.module.sys.entity.SysUser
     */
    SysUser getLoginUser(Principal principal);


    /*
     * @param pager:分页参数
     * @param en：实体
     * @Description: TODO(分页查询)
     * @date 2021/5/15 20:12
     * @returns com.ht.util.Pager
     */
    Pager findPage(Pager<SysUser> pager, SysUser en);

    /**
     * 保存用户
     * @param sysUser：用户实体
     */
    void saveObj(SysUser sysUser);

    /**
     * 重置密码
     * @param id:用户id
     */
    void resetPassword(String id);

    /**
     * 设置用户角色
     * @param sysUser
     */
    void setRoles(SysUser sysUser);

    /**
     * 修改密码
     * @param passWordDto
     */
    void updatePassword(PassWordDto passWordDto);

    /**
     * 注册
     * @param dto
     */
    void registered(RegisteredDto dto);
}
