package com.ams.module.bus.mapper.controller;

import com.ams.module.bus.entity.Cart;
import com.ams.module.bus.entity.OrderForm;
import com.ams.module.bus.service.ICartService;
import com.ams.util.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


/**
 * <p>
 * 购物车表 前端控制器
 * </p>
 *
 * @author Auto-generator
 * @since 2022/5/6
 */
@Api(tags = "购物车表 ")
@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private ICartService iCartService;

    @ApiOperation(value = "购买")
    @PostMapping("buy")
    public JsonResult buy(@Valid OrderForm orderForm){
        iCartService.buy(orderForm);
        return JsonResult.success();
    }

    /**
     * 清空购物车
     */
    @ApiOperation(value = "清空购物车")
    @PostMapping("empty")
    public JsonResult empty(){
        iCartService.empty();
        return JsonResult.success();
    }

    /**
     * 列表查询
     */
    @ApiOperation(value = "列表查询")
    @GetMapping("/findList")
    public JsonResult findPage(Cart cart){
        return JsonResult.success(iCartService.findList(cart));
    }

    /**
    * 分页查询
    */
    @ApiOperation(value = "分页查询")
    @GetMapping("/findPage")
    public JsonResult findPage(Pager<Cart> pager, Cart cart){
        return JsonResult.success(iCartService.page(pager));
    }

    /**
    * 通过id查询
    */
    @GetMapping("/get-by-id/{id}")
    @ApiOperation(value = "通过id查询")
    public JsonResult getById(@PathVariable(value = "id") String id){
        return JsonResult.success(iCartService.getById(id));
    }

    /**
    * 新增
    */
    @PostMapping("/add")
    @ApiOperation(value = "新增")
    public JsonResult add(Cart cart){
        CommMethod.beanCreate(cart);
        iCartService.add(cart);
        return JsonResult.success();
    }

    /**
    * 通过id删除
    */
    @DeleteMapping("/delete-by-id/{id}")
    @ApiOperation(value = "删除")
    public JsonResult delete(@PathVariable(value = "id") String id){
        iCartService.removeById(id);
        return JsonResult.success();
    }

    /**
    * 修改
    */
    @PutMapping("/update")
    @ApiOperation(value = "修改")
    public JsonResult updateById(Cart cart){
        CommMethod.beanUpdate(cart);
        iCartService.updateById(cart);
        return JsonResult.success();
    }
}
