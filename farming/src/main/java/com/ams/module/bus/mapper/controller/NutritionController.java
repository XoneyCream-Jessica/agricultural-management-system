package com.ams.module.bus.mapper.controller;

import com.ams.module.bus.entity.Nutrition;
import com.ams.module.bus.service.INutritionService;
import com.ams.util.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * <p>
 * 营养成分检测表 前端控制器
 * </p>
 *
 * @author Auto-generator
 * @since 2022-05-06
 */
@Api(tags = "营养成分检测表 ")
@RestController
@RequestMapping("/nutrition")
public class NutritionController {

    
    @Autowired
    private INutritionService iNutritionService;


    @ApiOperation(value = "列表查询")
    @GetMapping("/findList")
    public JsonResult findList( Nutrition en){
        return JsonResult.success(iNutritionService.findList(en));
    }



    /**
    * 分页查询
    */
    @ApiOperation(value = "分页查询")
    @GetMapping("/findPage")
    public JsonResult findPage(Pager<Nutrition> pager, Nutrition nutrition){
        return JsonResult.success(iNutritionService.page(pager));
    }


    /**
    * 通过id查询
    */
    @GetMapping("/get-by-id/{id}")
    @ApiOperation(value = "通过id查询")
    public JsonResult getById(@PathVariable(value = "id") String id){
        return JsonResult.success(iNutritionService.getById(id));
    }

    /**
    * 新增
    */
    @PostMapping("/add")
    @ApiOperation(value = "新增")
    public JsonResult add(Nutrition nutrition){
        CommMethod.beanCreate(nutrition);
        iNutritionService.save(nutrition);
        return JsonResult.success();
    }

    /**
    * 通过id删除
    */
    @DeleteMapping("/delete-by-id/{id}")
    @ApiOperation(value = "删除")
    public JsonResult delete(@PathVariable(value = "id") String id){
        iNutritionService.removeById(id);
        return JsonResult.success();
    }

    /**
    * 修改
    */
    @PutMapping("/update")
    @ApiOperation(value = "修改")
    public JsonResult updateById(Nutrition nutrition){
        CommMethod.beanUpdate(nutrition);
        iNutritionService.updateById(nutrition);
        return JsonResult.success();
    }

}
