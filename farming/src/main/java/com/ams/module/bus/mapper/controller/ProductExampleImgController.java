package com.ams.module.bus.mapper.controller;

import com.ams.module.bus.entity.ProductExampleImg;
import com.ams.module.bus.service.IProductExampleImgService;
import com.ams.util.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
/**
 * <p>
 * 产品实例图片表 前端控制器
 * </p>
 *
 * @author Auto-generator
 * @since 2022-05-07
 */
@Api(tags = "产品实例图片表 ")
@RestController
@RequestMapping("/product-example-img")
public class ProductExampleImgController {
    @Autowired
    private IProductExampleImgService iProductExampleImgService;
    /**
     * 列表查询
     */
    @ApiOperation(value = "列表查询")
    @GetMapping("/findList")
    public JsonResult findList( ProductExampleImg en){
        return JsonResult.success(iProductExampleImgService.findList(en));
    }

    /**
    * 分页查询
    */
    @ApiOperation(value = "分页查询")
    @GetMapping("/findPage")
    public JsonResult findPage(Pager<ProductExampleImg> pager, ProductExampleImg productExampleImg){
        return JsonResult.success(iProductExampleImgService.page(pager));
    }

    /**
    * 通过id查询
    */
    @GetMapping("/get-by-id/{id}")
    @ApiOperation(value = "通过id查询")
    public JsonResult getById(@PathVariable(value = "id") String id){
        return JsonResult.success(iProductExampleImgService.getById(id));
    }

    /**
    * 新增
    */
    @PostMapping("/add")
    @ApiOperation(value = "新增")
    public JsonResult add(ProductExampleImg productExampleImg){
        CommMethod.beanCreate(productExampleImg);
        iProductExampleImgService.save(productExampleImg);
        return JsonResult.success();
    }

    /**
    * 通过id删除
    */
    @DeleteMapping("/delete-by-id/{id}")
    @ApiOperation(value = "删除")
    public JsonResult delete(@PathVariable(value = "id") String id){
        iProductExampleImgService.removeById(id);
        return JsonResult.success();
    }

    /**
    * 修改
    */
    @PutMapping("/update")
    @ApiOperation(value = "修改")
    public JsonResult updateById(ProductExampleImg productExampleImg){
        iProductExampleImgService.updateById(productExampleImg);
        return JsonResult.success();
    }

}
