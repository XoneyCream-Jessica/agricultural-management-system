package com.ams.module.sys.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.List;


/**
 * <p>
 * 
 * </p>
 *
 * @author Auto-generator
 * @since 2022-05-07
 */

@TableName("sys_user")
@Data
public class SysUser  {


    /**
     * 主键
     */
    private String id;
    /**
     * 用户名
     */
    @NotBlank(message = "用户名不能为空")
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 昵称
     */
    @NotBlank(message = "昵称不能为空")
    private String name;

    /**
     * 昵称
     */
    @NotBlank(message = "性别不能为空")
    private String sex;

    /**
     * 昵称
     */
    @NotBlank(message = "电话不能为空")
    private String phone;

    /**
     * 创建时间
     */
    private LocalDateTime createDate;
    /**
     * 创建人
     */
    private String createUser;

    /**
     * 修改时间
     */
    private LocalDateTime updateDate;

    /**
     * 修改人
     */
    private String updateUser;


    /**
     * 角色名拼接字符串
     */
    @TableField(exist = false)
    private String roleNames;

    /**
     * 用户菜单
     */
    @TableField(exist = false)
    private List<SysMenu> sysMenus;

    /**
     * 用户按钮权限
     */
    @TableField(exist = false)
    private List<SysMenu> permissions;

    /**
     * 用户角色
     */
    @TableField(exist = false)
    private List<String> roles;



}
