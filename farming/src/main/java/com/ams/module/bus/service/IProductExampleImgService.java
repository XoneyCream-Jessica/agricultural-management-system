package com.ams.module.bus.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ams.module.bus.entity.ProductExampleImg;

import java.util.List;

/**
 * <p>
 * 产品实例图片表 服务类
 * </p>
 *
 * @author Auto-generator
 * @since 2022-05-07
 */
public interface IProductExampleImgService extends IService<ProductExampleImg> {

    List<ProductExampleImg> findList(ProductExampleImg en);
}
