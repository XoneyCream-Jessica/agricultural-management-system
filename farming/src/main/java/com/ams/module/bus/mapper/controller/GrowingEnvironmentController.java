package com.ams.module.bus.mapper.controller;

import com.ams.module.bus.entity.GrowingEnvironment;
import com.ams.module.bus.service.IGrowingEnvironmentService;
import com.ams.module.bus.service.IProductExampleService;
import com.ams.util.*;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ams.util.LanguageUtil;

import java.util.List;
import java.util.Map;


/**
 * <p>
 * 生长环境记录表 前端控制器
 * </p>
 *
 * @author Auto-generator
 * @since 2022/5/6
 */
@Api(tags = "生长环境记录表 ")
@RestController
@RequestMapping("/growing-environment")
public class GrowingEnvironmentController {


    @Autowired
    private IGrowingEnvironmentService iGrowingEnvironmentService;

    @Autowired
    private IProductExampleService iProductExampleService;

    @ApiOperation(value = "列表查询")
    @GetMapping("/findList")
    public JsonResult findList(GrowingEnvironment en) {
        List<GrowingEnvironment> list = iGrowingEnvironmentService.findList(en);
        if (list != null && list.size() > 0) {
            list = list.subList(0, list.size() > 50 ? 50 : list.size());
            for (int i = 0; i < list.size(); i++) {
                String pestName = list.get(i).getPestName();
                pestName = LanguageUtil.get(pestName);
                list.get(i).setPestName(pestName);
            }
        }
        return JsonResult.success(list);
    }

    /**
     * 分页查询
     */
    @ApiOperation(value = "分页查询")
    @GetMapping("/findPage")
    public JsonResult findPage(Pager<GrowingEnvironment> pager, GrowingEnvironment en) {
        IPage<GrowingEnvironment> page = iGrowingEnvironmentService.page(pager);
        for (GrowingEnvironment environment : page.getRecords()) {
            environment.setPestName(LanguageUtil.get(environment.getPestName()));
        }
        return JsonResult.success();
    }


    /**
     * 通过id查询
     */
    @GetMapping("/get-by-id/{id}")
    @ApiOperation(value = "通过id查询")
    public JsonResult getById(@PathVariable(value = "id") String id) {
        GrowingEnvironment env = iGrowingEnvironmentService.getById(id);
        env.setPestName(LanguageUtil.get(env.getPestName()));
        return JsonResult.success(env);
    }

    /**
     * 新增
     */
    @PostMapping("/add")
    @ApiOperation(value = "新增")
    public JsonResult add(GrowingEnvironment growingEnvironment) {
        CommMethod.beanCreate(growingEnvironment);
        iGrowingEnvironmentService.save(growingEnvironment);
        return JsonResult.success();
    }

    /**
     * 通过id删除
     */
    @DeleteMapping("/delete-by-id/{id}")
    @ApiOperation(value = "删除")
    public JsonResult delete(@PathVariable(value = "id") String id) {
        iGrowingEnvironmentService.removeById(id);
        return JsonResult.success();
    }

    /**
     * 修改
     */
    @PutMapping("/update")
    @ApiOperation(value = "修改")
    public JsonResult updateById(GrowingEnvironment growingEnvironment) {
        CommMethod.beanUpdate(growingEnvironment);
        iGrowingEnvironmentService.updateById(growingEnvironment);
        return JsonResult.success();
    }

    @GetMapping("/findProcGrowEnvList")
    @ApiOperation(value = "查询产品生长环境列表")
    public JsonResult findProcGrowEnvList() {
        return JsonResult.success(iGrowingEnvironmentService.findProcGrowEnvList());
    }

    @GetMapping("/findHumitureListByProcId/{productId}")
    @ApiOperation(value = "根据产品id查询温湿度")
    public JsonResult findHumitureListByProcId(@PathVariable(value = "productId") String productId) {
        return JsonResult.success(iGrowingEnvironmentService.findHumitureListByProcId(productId));
    }

    @ApiOperation(value = "列表查询")
    @GetMapping("/findProcGrowEnvListByPeId/{peid}")
    public JsonResult findProcGrowEnvList(@PathVariable(value = "peid") String peid) {
        GrowingEnvironment en = new GrowingEnvironment(iProductExampleService.getProductIdByPeId(peid));
        return findList(en);
    }

}
