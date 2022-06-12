package com.ams.module.sys.controller;

import com.ams.module.sys.dto.PassWordDto;
import com.ams.module.sys.dto.RegisteredDto;
import com.ams.module.sys.entity.SysUser;
import com.ams.module.sys.service.ISysUserService;
import com.ams.util.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;


/**
 * <p>
 *  用户表 前端控制器
 * </p>
 *
 * @author Auto-generator
 * @since 2022-05-07
 */
@RestController
@RequestMapping("/sys-user")
@Api(tags = "用户 ")
public class SysUserController {
    @Autowired
    private ISysUserService iSysUserService;

    @ApiOperation(value = "设置用户角色")
    @PostMapping("/setRoles")
    @PreAuthorize("hasAnyAuthority('SYS_USER_SET_ROLE')")
    public JsonResult setRoles(@RequestBody SysUser sysUser){
        iSysUserService.setRoles(sysUser);
        return JsonResult.success();
    }

    @ApiOperation(value = "重置密码")
    @PostMapping("/resetPassword")
    @PreAuthorize("hasAnyAuthority('SYS_USER_RESET_PASSWORD')")
    public JsonResult resetPassword(String id){
        iSysUserService.resetPassword(id);
        return JsonResult.success();
    }

    /**
     *
     * @Description: TODO(获取登录用户信息)
     * @Return com.ht.util.JsonResult
     * @Date 2022-05-07 13:45
     */
    @ApiOperation(value = "获取登录用户信息")
    @GetMapping("/getLoginUser")
    public JsonResult getLoginUser(Principal principal){
        return JsonResult.success(iSysUserService.getLoginUser(principal));
    }

    /**
    * 分页查询
    */
    @GetMapping("/findPage")
    @ApiOperation(value = "分页查询")
    public JsonResult findPage(Pager<SysUser> pager, SysUser en){
        return JsonResult.success(iSysUserService.findPage(pager,en));
    }

    /**
    * 通过id查询
    */
    @GetMapping("/get-by-id/{id}")
    @ApiOperation(value = "通过id查询")
    public JsonResult getById(@PathVariable(value = "id") String id){
        return JsonResult.success(iSysUserService.getById(id));
    }

    /**
    * 新增
    */
    @PostMapping("/add")
    @ApiOperation(value = "新增")
    @PreAuthorize("hasAnyAuthority('SYS_USER_ADD')")
    public JsonResult add(@Validated SysUser sysUser){
        iSysUserService.saveObj(sysUser);
        return JsonResult.success();
    }

    /**
    * 通过id删除
    */
    @DeleteMapping("/delete-by-id/{id}")
    @ApiOperation(value = "删除")
    @PreAuthorize("hasAnyAuthority('SYS_USER_DEL')")
    public JsonResult delete(@PathVariable(value = "id") String id){
        iSysUserService.removeById(id);
        return JsonResult.success();
    }

    /**
    * 修改
    */
    @PutMapping("/update")
    @ApiOperation(value = "修改")
    public JsonResult updateById(@Validated SysUser sysUser){
        iSysUserService.updateById(sysUser);
        return JsonResult.success();
    }

     /**
     * 修改
     */
    @PutMapping("/updatePassword")
    @ApiOperation(value = "修改密码")
    public JsonResult updatePassword(@Validated PassWordDto passWordDto){
        iSysUserService.updatePassword(passWordDto);
        return JsonResult.success();
    }

    /**
     * 注册
     */
    @PostMapping("/registered")
    @ApiOperation(value = "注册用户")
    public JsonResult registered(@Validated RegisteredDto dto){
        iSysUserService.registered(dto);
        return JsonResult.success();
    }

}
