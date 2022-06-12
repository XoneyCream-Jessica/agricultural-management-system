package com.ams.module.bus.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;


/**
 * <p>
 * 购物车表
 * </p>
 *
 * @author Auto-generator
 * @since  2022-05-07
 */

@TableName("cart")
@Data
public class Cart extends Model<Cart> {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId
    private String id;
    /**
     * 产品编号
     */
    private String peId;
    /**
     * 数量
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


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
