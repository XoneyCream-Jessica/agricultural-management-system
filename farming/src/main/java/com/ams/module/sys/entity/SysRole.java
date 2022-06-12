package com.ams.module.sys.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


/**
 * <p>
 * 
 * </p>
 *
 * @author Auto-generator
 * @since 2022-05-07
 */

@TableName("sys_role")
@Data
public class SysRole {


    /**
     * 角色表主键
     */
    private String id;
    /**
     * 角色编码
     */
    @NotBlank(message = "角色编码不能为空")
    private String code;
    /**
     * 角色名
     */
    @NotBlank(message = "角色名不能为空")
    private String name;
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
     * 菜单集合
     */
    @TableField(exist = false)
    private List<SysMenu> menus = new ArrayList<>();
}
