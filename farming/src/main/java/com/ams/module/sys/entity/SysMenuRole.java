package com.ams.module.sys.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;


/**
 * <p>
 * 权限菜单表
 * </p>
 *
 * @author Auto-generator
 * @since 2022-05-07
 */

@TableName("sys_menu_role")
@Data
public class SysMenuRole {



    /**
     * 主键
     */
    @TableId
    private String id;
    /**
     * 权限id
     */
    private String roleId;
    /**
     * 菜单id
     */
    private String menuId;



}
