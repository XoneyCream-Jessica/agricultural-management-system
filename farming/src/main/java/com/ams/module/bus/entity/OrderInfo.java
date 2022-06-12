package com.ams.module.bus.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;


/**
 * <p>
 * 订单表
 * </p>
 *
 * @author Auto-generator
 * @since 2022-05-07
 */

@TableName("order_info")
@Data
public class OrderInfo extends Model<OrderInfo> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId
    private String id;
    /**
     * 订单id
     */
    private String orderId;
    /**
     * 产品id
     */
    private String peId;
    /**
     * 产品数量
     */
    private Integer number;
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
     * 产品名
     */
    @TableField(exist = false)
    private String name;

    /**
     * 产品标题
     */
    @TableField(exist = false)
    private String title;


    /**
     * 产品价格
     */
    @TableField(exist = false)
    private String price;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
