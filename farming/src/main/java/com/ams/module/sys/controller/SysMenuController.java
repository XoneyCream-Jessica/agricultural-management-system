package com.ams.module.sys.controller;

import com.ams.module.sys.entity.SysMenu;
import com.ams.module.sys.service.ISysMenuService;
import com.ams.util.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


/**
 * <p>
 * 菜单表 前端控制器
 * </p>
 *
 * @author Auto-generator
 * @since 2022-05-07
 */
@RestController
@RequestMapping("/sys-menu")
@Api(tags = "菜单 ")
public class SysMenuController {

    
    @Autowired
    private ISysMenuService iSysMenuService;


    @GetMapping("/findMenuRole/{rid}")
    @ApiOperation(value = "按角色查询出菜单")
    public JsonResult findMenuRole(@PathVariable(value = "rid") String rid){
        return JsonResult.success(iSysMenuService.findMenuRole(rid));
    }



    @GetMapping("/findMenuTree")
    @ApiOperation(value = "查询出菜单树")
    public JsonResult findMenuTree(){
        return JsonResult.success(iSysMenuService.findMenuTree());
    }


    /**
    * 分页查询
    */
    @GetMapping("/findPage")
    @ApiOperation(value = "分页查询")
    public JsonResult findPage(Pager<SysMenu> pager, SysMenu sysMenu){
        return JsonResult.success(iSysMenuService.page(pager));
    }


    /**
    * 通过id查询
    */
    @GetMapping("/get-by-id/{id}")
    @ApiOperation(value = "通过id查询")
    public JsonResult getById(@PathVariable(value = "id") String id){
        return JsonResult.success(iSysMenuService.getById(id));
    }

    /**
    * 新增
    */
    @PostMapping("/add")
    @ApiOperation(value = "新增")
    @PreAuthorize("hasAnyAuthority('SYS_MENU_ADD')")
    public JsonResult add(@Validated SysMenu sysMenu){
        iSysMenuService.save(sysMenu);
        return JsonResult.success();
    }

    /**
    * 通过id删除
    */
    @DeleteMapping("/delete-by-id/{id}")
    @ApiOperation(value = "删除")
    @PreAuthorize("hasAnyAuthority('SYS_MENU_DEL')")
    public JsonResult delete(@PathVariable(value = "id") String id){
        iSysMenuService.delById(id);
        return JsonResult.success();
    }

    /**
    * 修改
    */
    @PutMapping("/update")
    @ApiOperation(value = "修改")
    @PreAuthorize("hasAnyAuthority('SYS_MENU_UPDATE')")
    public JsonResult updateById(@Validated SysMenu sysMenu){
        iSysMenuService.updateById(sysMenu);
        return JsonResult.success();
    }

}
