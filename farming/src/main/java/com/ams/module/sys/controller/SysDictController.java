package com.ams.module.sys.controller;

import com.ams.module.sys.entity.SysDict;
import com.ams.module.sys.service.ISysDictService;
import com.ams.util.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


/**
 * <p>
 * 字典表 前端控制器
 * </p>
 *
 * @author Auto-generator
 * @since 2022-05-07
 */
@Api(tags = "字典表 ")
@RestController
@RequestMapping("/sys-dict")
public class SysDictController {

    
    @Autowired
    private ISysDictService iSysDictService;

    /**
    * 分页查询
    */
    @ApiOperation(value = "分页查询")
    @GetMapping("/findPage")
    public JsonResult findPage(Pager<SysDict> pager, SysDict sysDict){
        return JsonResult.success(iSysDictService.findPage(pager,sysDict));
    }


    /**
    * 通过id查询
    */
    @GetMapping("/get-by-id/{id}")
    @ApiOperation(value = "通过id查询")
    public JsonResult getById(@PathVariable(value = "id") String id){
        return JsonResult.success(iSysDictService.getById(id));
    }

    /**
    * 新增
    */
    @PostMapping("/add")
    @ApiOperation(value = "新增")
    @PreAuthorize("hasAnyAuthority('SYS_DICT_ADD')")
    public JsonResult add(@Validated SysDict sysDict){
        CommMethod.beanCreate(sysDict);
        iSysDictService.save(sysDict);
        return JsonResult.success();
    }

    /**
    * 通过id删除
    */
    @DeleteMapping("/delete-by-id/{id}")
    @ApiOperation(value = "删除")
    @PreAuthorize("hasAnyAuthority('SYS_DICT_DEL')")
    public JsonResult delete(@PathVariable(value = "id") String id){
        iSysDictService.removeById(id);
        return JsonResult.success();
    }

    /**
    * 修改
    */
    @PutMapping("/update")
    @ApiOperation(value = "修改")
    @PreAuthorize("hasAnyAuthority('SYS_DICT_UPDATE')")
    public JsonResult updateById(@Validated SysDict sysDict){
        CommMethod.beanUpdate(sysDict);
        iSysDictService.updateById(sysDict);
        return JsonResult.success();
    }

}
