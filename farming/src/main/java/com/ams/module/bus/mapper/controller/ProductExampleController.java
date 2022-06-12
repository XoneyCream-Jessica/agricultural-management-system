package com.ams.module.bus.mapper.controller;

import com.ams.module.bus.entity.ProductExample;
import com.ams.module.bus.service.IProductExampleService;
import com.ams.util.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Auto-generator
 * @since 2022-05-07
 */
@Api(tags = " ")
@RestController
@RequestMapping("/product-example")
public class ProductExampleController {
    @Autowired
    private IProductExampleService iProductExampleService;

    /**
     * 修改
     */
    @PutMapping("/putInventory")
    @ApiOperation(value = "入库")
    public JsonResult putInventory(String id,int inventory){
        iProductExampleService.putInventory(id,inventory);
        return JsonResult.success();
    }

    /**
    * 分页查询
    */
    @ApiOperation(value = "分页查询")
    @GetMapping("/findPage")
    public JsonResult findPage(Pager<ProductExample> pager, ProductExample productExample){
        return JsonResult.success(iProductExampleService.findPage(pager,productExample));
    }

    /**
    * 通过id查询
    */
    @GetMapping("/get-by-id/{id}")
    @ApiOperation(value = "通过id查询")
    public JsonResult getById(@PathVariable(value = "id") String id){
        return JsonResult.success(iProductExampleService.findById(id));
    }

    /**
    * 新增
    */
    @PostMapping("/add")
    @ApiOperation(value = "新增")
    public JsonResult add(ProductExample productExample){
        CommMethod.beanCreate(productExample);
        productExample.setInventory(0);
        iProductExampleService.save(productExample);
        return JsonResult.success();
    }

    /**
    * 通过id删除
    */
    @DeleteMapping("/delete-by-id/{id}")
    @ApiOperation(value = "删除")
    public JsonResult delete(@PathVariable(value = "id") String id){
        iProductExampleService.removeById(id);
        return JsonResult.success();
    }

    /**
    * 修改
    */
    @PutMapping("/update")
    @ApiOperation(value = "修改")
    public JsonResult updateById(ProductExample productExample){
        CommMethod.beanUpdate(productExample);
        iProductExampleService.updateById(productExample);
        return JsonResult.success();
    }


    @GetMapping("/findTitlePriceList")
    @ApiOperation(value = "查询标题价格列表")
    public JsonResult findTitlePriceList(){
        return JsonResult.success(iProductExampleService.findTitlePriceList());
    }

    @GetMapping("/getProductIdByPeId")
    @ApiOperation(value = "根据peid查找产品id")
    public JsonResult getProductIdByPeId(String peId){
        int a=1;
        int b=2;
        String c = iProductExampleService.getProductIdByPeId(peId);
        return JsonResult.success(iProductExampleService.getProductIdByPeId(peId));
    }
}
