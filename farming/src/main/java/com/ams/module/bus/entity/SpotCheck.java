package com.ams.module.bus.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;


/**
 * <p>
 * 产品抽查表
 * </p>
 *
 * @author Auto-generator
 * @since 2022-05-07
 */

@TableName("spot_check")
@Data
public class SpotCheck extends Model<SpotCheck> {

    private static final long serialVersionUID = 1L;

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
     * 感官指标检查结果(接码表-check_situation
     */
    private String sensory;
    /**
     * 农药最大残留限量(接码表-check_situation
     */
    private String pesticide;
    /**
     * 重金属及有害物质限量检查结果(接码表-check_situation
     */
    private String metal;
    /**
     * 病原菌检查情况(接码表-check_situation
     */
    private String germs;
    /**
     * 综合评价(接码表-check_situation
     */
    private String comprehensive;
    /**
     * 检查单位
     */
    private String checkUnit;
    /**
     * 检查人
     */
    private String checkPeople;
    /**
     * 检查日期
     */
    private LocalDate checkDate;
    /**
     * 备注
     */
    private String note;
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


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
