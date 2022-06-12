package com.ams.module.bus.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ams.module.bus.entity.ProductExampleImg;
import com.ams.module.bus.mapper.ProductExampleImgMapper;
import com.ams.module.bus.service.IProductExampleImgService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 产品实例图片表 服务实现类
 * </p>
 *
 * @author Auto-generator
 * @since 2022-05-07
 */
@Service
public class ProductExampleImgServiceImpl extends ServiceImpl<ProductExampleImgMapper, ProductExampleImg> implements IProductExampleImgService {

    @Override
    public List<ProductExampleImg> findList(ProductExampleImg en) {
        QueryWrapper<ProductExampleImg> qw=new QueryWrapper<>();
        if(StrUtil.isNotEmpty(en.getPeId())){
            qw.eq("tab.pe_id",en.getPeId());
        }
        qw.orderByDesc("tab.create_date");
        return baseMapper.findList(qw);
    }
}
