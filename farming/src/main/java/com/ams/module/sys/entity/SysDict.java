package com.ams.module.sys.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;


/**
 * <p>
 * 字典表
 * </p>
 *
 * @author Auto-generator
 * @since 2022-05-07
 */

@TableName("sys_dict")
@Data
public class SysDict  {

    /**
     * 主键
     */
    @TableId
    private String id;
    /**
     * 字典名称
     */
    @NotBlank(message = "字典名不能为空")
    private String dictName;
    /**
     * 字典编码
     */
    @NotBlank(message = "字典编码不能为空")
    private String dictCode;
    /**
     * 描述
     */
    @TableField(value = "`desc`")
    private String desc;
    /**
     * 逻辑删除
     */
    @TableLogic
    private Integer delFlag;

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
}
