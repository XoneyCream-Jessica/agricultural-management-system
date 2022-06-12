package com.ams.module.bus.mapper.controller;

import com.ams.module.bus.entity.SpotCheck;
import com.ams.module.bus.service.ISpotCheckService;
import com.ams.util.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
/**
 * <p>
 * 产品抽查表 前端控制器
 * </p>
 *
 * @author Auto-generator
 * @since 2021-06-01
 */
@Api(tags = "产品抽查表 ")
@RestController
@RequestMapping("/spot-check")
public class SpotCheckController {
    @Autowired
    private ISpotCheckService iSpotCheckService;

    /**
     * 列表查询
     */
    @ApiOperation(value = "列表查询")
    @GetMapping("/findList")
    public JsonResult findList(SpotCheck spotCheck){
        return JsonResult.success(iSpotCheckService.findList(spotCheck));
    }

    /**
    * 分页查询
    */
    @ApiOperation(value = "分页查询")
    @GetMapping("/findPage")
    public JsonResult findPage(Pager<SpotCheck> pager, SpotCheck spotCheck){
        return JsonResult.success(iSpotCheckService.page(pager));
    }

    /**
    * 通过id查询
    */
    @GetMapping("/get-by-id/{id}")
    @ApiOperation(value = "通过id查询")
    public JsonResult getById(@PathVariable(value = "id") String id){
        return JsonResult.success(iSpotCheckService.getById(id));
    }

    /**
    * 新增
    */
    @PostMapping("/add")
    @ApiOperation(value = "新增")
    public JsonResult add(SpotCheck spotCheck){
        CommMethod.beanCreate(spotCheck);
        iSpotCheckService.save(spotCheck);
        return JsonResult.success();
    }

    /**
    * 通过id删除
    */
    @DeleteMapping("/delete-by-id/{id}")
    @ApiOperation(value = "删除")
    public JsonResult delete(@PathVariable(value = "id") String id){
        iSpotCheckService.removeById(id);
        return JsonResult.success();
    }

    /**
    * 修改
    */
    @PutMapping("/update")
    @ApiOperation(value = "修改")
    public JsonResult updateById(SpotCheck spotCheck){
        CommMethod.beanUpdate(spotCheck);
        iSpotCheckService.updateById(spotCheck);
        return JsonResult.success();
    }

}
