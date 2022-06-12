package com.ams.module.bus.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;


/**
 * <p>
 * 营养成分检测表
 * </p>
 *
 * @author Auto-generator
 * @since 2022-05-07
 */

@TableName("nutrition")
@Data
public class Nutrition extends Model<Nutrition> {

    private static final long serialVersionUID = 1L;
    public Nutrition(String peId){
        this.peId=peId;
    }

    /**
     * 主键id
     */
    @TableId
    private String id;
    /**
     * 产品实例id
     */
    private String peId;
    /**
     * 蛋白自含量(克/100克)
     */
    private BigDecimal protein;
    /**
     * 碳水化合物(接码表-carbohydrate)
     */
    private String carbohydrate;
    /**
     * 矿物质(接码表-minerals)
     */
    private String minerals;
    /**
     * 水分含量(%)
     */
    private BigDecimal moisture;
    /**
     * 膳食纤维含量
     */
    private BigDecimal df;
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
     * 逻辑删除
     */
    @TableLogic
    private Integer delFlag;

    /**
     * 录入人
     */
    @TableField(exist = false)
    private String createUserName;

    /**
     * 创建人联系方式
     */
    @TableField(exist = false)
    private String createUserPhone;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
