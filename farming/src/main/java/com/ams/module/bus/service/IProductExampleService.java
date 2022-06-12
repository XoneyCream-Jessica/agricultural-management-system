package com.ams.module.bus.service;

import com.ams.module.bus.vo.CartVo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ams.module.bus.entity.ProductExample;
import com.ams.util.Pager;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Auto-generator
 * @since 2022-05-07
 */
public interface IProductExampleService extends IService<ProductExample> {

    Pager<ProductExample> findPage(Pager<ProductExample> pager, ProductExample productExample);

    void putInventory(String id, int inventory);

    ProductExample findById(String id);

    List<ProductExample>  findTitlePriceList();

    String getProductIdByPeId(String peId);
}
