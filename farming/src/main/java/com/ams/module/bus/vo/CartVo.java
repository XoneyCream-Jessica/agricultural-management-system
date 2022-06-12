package com.ams.module.bus.vo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @ProjectName: ams
 * @ClassName: CartVo
 * @Author: chenxi
 * @Description: 购物车vo
 * @Date: 2022-05-07 14:57
 */
@Data
public class CartVo {
    //购物车id
    private String id;

    //产品批号
    private String exampleId;

    //产品id
    private String productId;

    //产品图片
    private String productImg;

    //产品名
    private String productName;

    //标题
    private String title;

    //产品保质期
    private Integer shelfLifeNum;

    //产品保质期单位
    private String shelfLifeUnit;

    //售价
    private BigDecimal price;

    //售卖单位
    private String priceUnit;

    //购买数量
    private Integer number;
}