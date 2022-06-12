package com.ams.module.sys.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.List;


/**
 * <p>
 * 菜单表
 * </p>
 *
 * @author Auto-generator
 * @since 2022-05-07
 */

@TableName("sys_menu")
@Data
public class SysMenu {


    /**
     * 菜单id
     */
    @TableId
    private String id;
    /**
     * 上级菜单
     */
    private String pid;
    /**
     * 菜单名
     */
    @NotBlank(message = "名称不能为空")
    private String name;
    /**
     * 编码
     */
    @NotBlank(message = "编码不能为空")
    private String code;
    /**
     * 图标
     */
    private String icon;
    /**
     * 菜单路径
     */
    private String path;
    /**
     * 菜单类型：0：菜单：1：页面：2：按钮
     */
    @NotBlank(message = "类型不能为空")
    private String type;
    /**
     * 排序
     */
    private Integer sort;

    /**
     * 菜单子项
     */
    @TableField(exist = false)
    private List<SysMenu> children;


}
