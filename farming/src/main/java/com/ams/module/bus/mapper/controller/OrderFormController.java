package com.ams.module.bus.mapper.controller;

import com.ams.module.bus.entity.OrderForm;
import com.ams.module.bus.service.IOrderFormService;
import com.ams.util.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * <p>
 * 订单表 前端控制器
 * </p>
 *
 * @author Auto-generator
 * @since 2022-05-07
 */
@Api(tags = "订单表 ")
@RestController
@RequestMapping("/order-form")
public class OrderFormController {
    @Autowired
    private IOrderFormService iOrderFormService;

    /**
     * 确认收货
     */
    @ApiOperation(value = "确认收货")
    @PostMapping("/confirmGoods")
    public JsonResult confirmGoods(String id){
        iOrderFormService.confirmGoods(id);
        return JsonResult.success();
    }

    /**
     * 确认收货
     */
    @ApiOperation(value = "确认发货")
    @PostMapping("/confirmDelivery")
    public JsonResult confirmDelivery(String id){
        iOrderFormService.confirmDelivery(id);
        return JsonResult.success();
    }

    /**
    * 分页查询
    */
    @ApiOperation(value = "分页查询")
    @GetMapping("/findPage")
    public JsonResult findPage(Pager<OrderForm> pager, OrderForm orderForm){
        return JsonResult.success(iOrderFormService.findPage(pager,orderForm));
    }

    /**
    * 通过id查询
    */
    @GetMapping("/get-by-id/{id}")
    @ApiOperation(value = "通过id查询")
    public JsonResult getById(@PathVariable(value = "id") String id){
        return JsonResult.success(iOrderFormService.getById(id));
    }

    /**
    * 新增
    */
    @PostMapping("/add")
    @ApiOperation(value = "新增")
    public JsonResult add(OrderForm orderForm){
        CommMethod.beanCreate(orderForm);
        iOrderFormService.save(orderForm);
        return JsonResult.success();
    }

    /**
    * 通过id删除
    */
    @DeleteMapping("/delete-by-id/{id}")
    @ApiOperation(value = "删除")
    public JsonResult delete(@PathVariable(value = "id") String id){
        iOrderFormService.removeById(id);
        return JsonResult.success();
    }

    /**
    * 修改
    */
    @PutMapping("/update")
    @ApiOperation(value = "修改")
    public JsonResult updateById(OrderForm orderForm){
        CommMethod.beanUpdate(orderForm);
        iOrderFormService.updateById(orderForm);
        return JsonResult.success();
    }
}
