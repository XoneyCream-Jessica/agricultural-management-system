package com.ams.module.bus.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;


/**
 * <p>
 * 订单表
 * </p>
 *
 * @author Auto-generator
 * @since 2022-05-07
 */

@TableName("order_form")
@Data
public class OrderForm extends Model<OrderForm> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId
    private String id;
    /**
     * 订单金额
     */
    private BigDecimal money;
    /**
     * 收货地址
     */
    @NotEmpty(message = "地址不能为空")
    private String address;
    /**
     * 联系电话
     */
    @NotEmpty(message = "联系电话不能为空")
    private String phone;
    /**
     * 联系人
     */
    @NotEmpty(message = "联系人不能为空")
    private String contact;
    /**
     * 订单状态（01:待发货，02:已发货；03:已收货）
     */
    private String state;
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

    /**
     * 是否查询本人
     */
    @TableField(exist = false)
    private Boolean isMe=false;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
