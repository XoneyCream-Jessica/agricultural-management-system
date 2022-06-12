package com.ams.module.bus.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;


/**
 * <p>
 * 生长环境记录表
 * </p>
 *
 * @author Auto-generator
 * @since 2022-05-07
 */

@TableName("growing_environment")
@Data
public class GrowingEnvironment extends Model<GrowingEnvironment> {

    private static final long serialVersionUID = 1L;

    public GrowingEnvironment(String productId) {
        this.productId = productId;
    }

    /**
     * 主键id
     */
    @TableId
    private String id;
    /**
     * 产品实例id
     */
    private String productId;
    /**
     * 用水量/kg
     */
    private BigDecimal waterAmount;
    /**
     * 施肥量/kg
     */
    private BigDecimal fertilizationAmount;
    /**
     * 施肥时间
     */
    private LocalTime fertilizationDate;
    /**
     * 是否除草(接码表-yes_or_no)
     */
    private String isWeeding;
    /**
     * 是否使用农药(接码表-yes_or_no)
     */
    private String isPesticide;
    /**
     * 农药名
     */
    private String pesticideName;
    /**
     * 农药使用量/kg
     */
    private BigDecimal pesticideAmount;
    /**
     * 土壤检测情况(接码表-soil_conditions)
     */
    private String soilConditions;
    /**
     * 水质检测情况(接码表-water_quality)
     */
    private String waterQuality;
    /**
     * 害虫名
     */
    private String pestName;
    /**
     * 光照时长
     */
    private BigDecimal illuminationTime;
    /**
     * 温度
     */
    private BigDecimal temperature;
    /**
     * 湿度
     */
    private BigDecimal humidity;
    /**
     * 采集时间
     */
    private LocalDateTime collectDate;
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
     * 软删除
     */
    @TableLogic
    private Integer delFlag;

    /**
     * 创建人
     */
    @TableField(exist = false)
    private String createUserName;

    /**
     * 创建人联系方式
     */
    @TableField(exist = false)
    private String createUserPhone;

    /**
     * isExist
     */
    @TableField(exist = false)
    private boolean isExist;
}
