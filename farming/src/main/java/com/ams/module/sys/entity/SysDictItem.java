package com.ams.module.sys.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.validation.constraints.NotBlank;


/**
 * <p>
 * 字典项表
 * </p>
 *
 * @author Auto-generator
 * @since 2022-05-07
 */

@TableName("sys_dict_item")
@Data
public class SysDictItem {

    @TableId
    private String id;
    /**
     * 字典id
     */
    private String dictId;
    /**
     * 字典项文本
     */
    @NotBlank(message = "字典项文本不能为空")
    private String label;
    /**
     * 字典项值
     */
    @NotBlank(message = "字典项值不能为空")
    private String val;
    /**
     * 描述
     */
    @TableField(value = "`desc`")
    private String desc;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 逻辑删除
     */
    @TableLogic
    private Integer delFlag;



}
