package com.ams.module.bus.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;


/**
 * <p>
 * 产品实例图片表
 * </p>
 *
 * @author Auto-generator
 * @since 2022-05-07
 */

@TableName("product_example_img")
@Data
public class ProductExampleImg extends Model<ProductExampleImg> {

    private static final long serialVersionUID = 1L;

    public ProductExampleImg(String peId){
        this.peId=peId;
    }

    /**
     * 主键
     */
    private String id;
    /**
     * 产品实例id
     */
    private String peId;
    /**
     * 地址路径
     */
    private String path;

    /**
     * 创建人
     */
    private String createUser;

    /**
     * 创建时间
     */
    private LocalDateTime createDate;

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

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
