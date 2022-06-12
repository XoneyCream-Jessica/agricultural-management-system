package com.ams.module.bus.service.impl;

import cn.hutool.core.util.StrUtil;
import com.ams.module.bus.entity.*;
import com.ams.util.UserUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ams.abnormal.AmsException;
import com.ams.module.bus.mapper.ProductExampleMapper;
import com.ams.module.bus.service.*;
import com.ams.util.Pager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Auto-generator
 * @since 2022-05-07
 */
@Service
public class ProductExampleServiceImpl extends ServiceImpl<ProductExampleMapper, ProductExample> implements IProductExampleService {
    @Autowired
    private IProductExampleImgService iProductExampleImgService;
    @Autowired
    private IGrowingEnvironmentService iGrowingEnvironmentService;
    @Autowired
    private INutritionService iNutritionService;
    @Autowired
    private IProductService iProductService;

    @Override
    public Pager<ProductExample> findPage(Pager<ProductExample> pager, ProductExample en) {
        QueryWrapper<ProductExample> qw=new QueryWrapper<>();
        if(StrUtil.isNotEmpty(en.getCreateUser())){
            qw.eq("tab.create_user",en.getCreateUser());
        }
        if(StrUtil.isNotEmpty(en.getProducer())){
            qw.eq("tab.producer",en.getProducer());
        }
        if(StrUtil.isNotEmpty(en.getProductId())){
            qw.eq("tab.product_id",en.getProductId());
        }
        if(StrUtil.isNotEmpty(en.getTitle())){
            qw.like("tab.title",en.getTitle());
        }
        if(StrUtil.isNotEmpty(en.getCreateUserName())){
            qw.like("tab.create_user_name",en.getCreateUserName());
        }
        if(StrUtil.isNotEmpty(en.getProductType())){
            qw.eq("tab.product_type",en.getProductType());
        }
        if(StrUtil.isNotEmpty(en.getProductName())){
            qw.like("tab.product_name",en.getProductName());
        }
        //查询出关联的图片
        baseMapper.findPage(pager,qw);
        pager.getRecords().stream().forEach(x->{
            //查询出生长图片
            x.setProductExampleImgs(iProductExampleImgService.findList(new ProductExampleImg(x.getId())));
        });

        return pager;
    }

    @Override
    public void putInventory(String id, int inventory) {
        //查询出当前实例
        ProductExample en=baseMapper.selectById(id);
        en.setInventory(en.getInventory()+inventory);
        if(en.getInventory()<0){
            throw new AmsException("库存不足");
        }

        baseMapper.updateById(en);
    }

    @Override
    public ProductExample findById(String id) {
        //查询出实例
        ProductExample temp=baseMapper.findById(id);
        if(temp==null){
            throw new AmsException("当前批号下的产品不存在");
        }
        //查询出产品对象
        temp.setProduct(iProductService.findById(temp.getProductId()));
        //查询出生长图片
        temp.setProductExampleImgs(iProductExampleImgService.findList(new ProductExampleImg(id)));
        //查询出生长环境
        temp.setGrowingEnvironments(iGrowingEnvironmentService.findList(new GrowingEnvironment(temp.getProductId())));
        //查询营养成分
        temp.setNutritions(iNutritionService.findList(new Nutrition(id)));
        return temp;
    }

    @Override
    public List<ProductExample> findTitlePriceList() {
        return baseMapper.findTitlePriceList();
    }

    @Override
    public String getProductIdByPeId(String peId){
        return baseMapper.getProductIdByPeId(peId);
    }
}
