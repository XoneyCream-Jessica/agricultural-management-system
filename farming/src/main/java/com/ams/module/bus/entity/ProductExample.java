package com.ams.module.bus.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
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

@TableName("product_example")
@Data
public class ProductExample extends Model<ProductExample> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键产品编码/批号
     */
    @TableId
    private String id;
    /**
     * 产品id
     */
    private String productId;

    /**
     * 标题
     */
    private String title;
    /**
     * 售价
     */
    private BigDecimal price;
    /**
     * 售卖单位-关联码表price_unit
     */
    private String unit;
    /**
     * 生产日期
     */
    private LocalDateTime productionDate;
    /**
     * 生产地
     */
    private String producer;
    /**
     * 库存数量
     */
    private Integer inventory;
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
     * 创建人名
     */
    @TableField(exist = false)
    private String createUserName;

    /**
     * 创建人联系方式
     */
    @TableField(exist = false)
    private String createUserPhone;
    /**
     * 产品名
     */
    @TableField(exist = false)
    private String productName;

    /**
     * 产品类型
     */
    @TableField(exist = false)
    private String productType;


    /**
     * 产品
     */
    @TableField(exist = false)
    private Product product;


    /**
     * 产品生长图片
     */
    @TableField(exist = false)
    private List<ProductExampleImg> productExampleImgs;

    /**
     * 营养成分检测
     */
    @TableField(exist = false)
    private List<Nutrition> nutritions;

    /**
     * 生长环境
     */
    @TableField(exist = false)
    private List<GrowingEnvironment> growingEnvironments;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
