package com.ams.module.bus.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;


/**
 * <p>
 * 
 * </p>
 *
 * @author Auto-generator
 * @since 2022-05-07
 */

@TableName("product")
@Data
public class Product extends Model<Product> {

    private static final long serialVersionUID = 1L;

    /**
     * 产品表id
     */
    @TableId
    private String id;
    /**
     * 图片
     */
    private String img;
    /**
     * 产品名
     */
    private String name;
    /**
     * 产品类别-接码表product_type
     */
    private String type;
    /**
     * 总量/g
     */
    private BigDecimal total;
    /**
     * 保质期
     */
    private Integer shelfLifeNum;
    /**
     * 保质期单位-接码表shelf_life_unit
     */
    private String unit;
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
     * 创建人名
     */
    @TableField(exist = false)
    private String createUserName;

    /**
     * 创建人联系方式
     */
    @TableField(exist = false)
    private String createUserPhone;

    private Long listenerId;
}
