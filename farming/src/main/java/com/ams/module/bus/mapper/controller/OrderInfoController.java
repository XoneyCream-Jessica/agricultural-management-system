package com.ams.module.bus.mapper.controller;

import com.ams.module.bus.entity.OrderInfo;
import com.ams.module.bus.service.IOrderInfoService;
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
@RequestMapping("/order-info")
public class OrderInfoController {
    @Autowired
    private IOrderInfoService iOrderInfoService;
    /**
     * 列表查询
     */
    @ApiOperation(value = "findList")
    @GetMapping("/findList")
    public JsonResult findList(OrderInfo orderInfo){
        return JsonResult.success(iOrderInfoService.findList(orderInfo));
    }

    /**
    * 分页查询
    */
    @ApiOperation(value = "分页查询")
    @GetMapping("/findPage")
    public JsonResult findPage(Pager<OrderInfo> pager, OrderInfo orderInfo){
        return JsonResult.success(iOrderInfoService.page(pager));
    }

    /**
    * 通过id查询
    */
    @GetMapping("/get-by-id/{id}")
    @ApiOperation(value = "通过id查询")
    public JsonResult getById(@PathVariable(value = "id") String id){
        return JsonResult.success(iOrderInfoService.getById(id));
    }

    /**
    * 新增
    */
    @PostMapping("/add")
    @ApiOperation(value = "新增")
    public JsonResult add(OrderInfo orderInfo){
        CommMethod.beanCreate(orderInfo);
        iOrderInfoService.save(orderInfo);
        return JsonResult.success();
    }

    /**
    * 通过id删除
    */
    @DeleteMapping("/delete-by-id/{id}")
    @ApiOperation(value = "删除")
    public JsonResult delete(@PathVariable(value = "id") String id){
        iOrderInfoService.removeById(id);
        return JsonResult.success();
    }

    /**
    * 修改
    */
    @PutMapping("/update")
    @ApiOperation(value = "修改")
    public JsonResult updateById(OrderInfo orderInfo){
        CommMethod.beanUpdate(orderInfo);
        iOrderInfoService.updateById(orderInfo);
        return JsonResult.success();
    }

}
