package com.ams.module.sys.controller;

import com.ams.module.sys.entity.SysDictItem;
import com.ams.module.sys.service.ISysDictItemService;
import com.ams.util.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


/**
 * <p>
 * 字典项表 前端控制器
 * </p>
 *
 * @author Auto-generator
 * @since 2022-05-07
 */
@Api(tags = "字典项表 ")
@RestController
@RequestMapping("/sys-dict-item")
public class SysDictItemController {

    
    @Autowired
    private ISysDictItemService iSysDictItemService;



    @ApiOperation(value = "通过码表Code查询值")
    @GetMapping("/findByDictCode/{code}")
    public JsonResult findByDictCode(@PathVariable(value = "code") String code){
        return JsonResult.success(iSysDictItemService.findByDictCode(code));
    }


    /**
    * 列表查询
    */
    @ApiOperation(value = "列表查询")
    @GetMapping("/findList")
    public JsonResult findList(SysDictItem en){
        return JsonResult.success(iSysDictItemService.findList(en));
    }




    /**
    * 新增
    */
    @PostMapping("/add")
    @ApiOperation(value = "新增")
    public JsonResult add(@Validated SysDictItem sysDictItem){
        CommMethod.beanCreate(sysDictItem);
        iSysDictItemService.save(sysDictItem);
        return JsonResult.success();
    }

    /**
    * 通过id删除
    */
    @DeleteMapping("/delete-by-id/{id}")
    @ApiOperation(value = "删除")
    public JsonResult delete(@PathVariable(value = "id") String id){
        iSysDictItemService.removeById(id);
        return JsonResult.success();
    }

    /**
    * 修改
    */
    @PutMapping("/update")
    @ApiOperation(value = "修改")
    public JsonResult updateById(@Validated SysDictItem sysDictItem){
        CommMethod.beanUpdate(sysDictItem);
        iSysDictItemService.updateById(sysDictItem);
        return JsonResult.success();
    }

}
