package com.ams.module.sys.controller;


import com.ams.module.sys.entity.SysRole;
import com.ams.module.sys.service.ISysRoleService;
import com.ams.util.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;


/**
 * <p>
 *  角色表 前端控制器
 * </p>
 *
 * @author Auto-generator
 * @since 2022-05-07
 */
@RestController
@RequestMapping("/sys-role")
@Api(tags = "角色 ")
public class SysRoleController {

    
    @Autowired
    private ISysRoleService iSysRoleService;

    @PostMapping("/setMenuByRole")
    @ApiOperation(value = "给当前角色设置菜单")
    public JsonResult setMenuByRole(@RequestBody SysRole sysRole) {
        iSysRoleService.setMenuByRole(sysRole);
        return JsonResult.success();
    }

    /**
    * 分页查询
    */
    @GetMapping("/findPage")
    @ApiOperation(value = "分页查询")
    public JsonResult findPage(Pager<SysRole> pager, SysRole en){
        return JsonResult.success(iSysRoleService.findPage(pager,en));
    }

    /*
     * @param en:角色实体
     * @Description: TODO(查询所有角色)
     * @date 2021/5/15 20:30
     * @returns com.ht.util.JsonResult
     */
    @GetMapping("/findList")
    @ApiOperation(value = "查询角色")
    public JsonResult findList( SysRole en){
        return JsonResult.success(iSysRoleService.findList(en));
    }

    /**
    * 通过id查询
    */
    @GetMapping("/get-by-id/{id}")
    @ApiOperation(value = "通过id查询")
    public JsonResult getById(@PathVariable(value = "id") String id){
        return JsonResult.success(iSysRoleService.getById(id));
    }

    /**
    * 新增
    */
    @PostMapping("/add")
    @ApiOperation(value = "新增")
    @PreAuthorize("hasAnyAuthority('SYS_ROLE_ADD')")
    public JsonResult add(@Validated SysRole sysRole){
        sysRole.setCreateDate(LocalDateTime.now());
        sysRole.setCreateUser(UserUtil.getUserId());
        iSysRoleService.save(sysRole);
        return JsonResult.success();
    }

    /**
    * 通过id删除
    */
    @DeleteMapping("/delete-by-id/{id}")
    @ApiOperation(value = "删除")
    @PreAuthorize("hasAnyAuthority('SYS_ROLE_DEL')")
    public JsonResult delete(@PathVariable(value = "id") String id){
        iSysRoleService.removeById(id);
        return JsonResult.success();
    }

    /**
    * 修改
    */
    @PutMapping("/update")
    @ApiOperation(value = "修改")
    @PreAuthorize("hasAnyAuthority('SYS_ROLE_UPDATE')")
    public JsonResult updateById(@Validated SysRole sysRole){
        iSysRoleService.updateById(sysRole);
        return JsonResult.success();
    }

}
