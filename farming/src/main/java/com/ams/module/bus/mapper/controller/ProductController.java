package com.ams.module.bus.mapper.controller;

import com.ams.module.bus.entity.Product;
import com.ams.module.bus.service.IProductService;
import com.ams.module.bus.vo.BindSensorVo;
import com.ams.module.bus.vo.SensorBandVo;
import com.ams.util.CommMethod;
import com.ams.util.JsonResult;
import com.ams.util.Pager;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author Auto-generator
 * @since 2022-05-07
 */
@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private IProductService iProductService;

    /**
     * 列表查询
     */
    @GetMapping("/findList")
    public JsonResult findList(Product product) {
        return JsonResult.success(iProductService.findList(product));
    }


    /**
     * 分页查询
     */
    @GetMapping("/findPage")
    public JsonResult findPage(Pager<Product> pager, Product product) {
        return JsonResult.success(iProductService.findPage(pager, product));
    }


    /**
     * 通过id查询
     */
    @GetMapping("/get-by-id/{id}")
    public JsonResult getById(@PathVariable(value = "id") String id) {
        return JsonResult.success(iProductService.findById(id));
    }

    /**
     * 新增
     */
    @PostMapping("/add")
    public JsonResult add(Product product) {
        CommMethod.beanCreate(product);
        iProductService.save(product);
        return JsonResult.success();
    }

    /**
     * 通过id删除
     */
    @DeleteMapping("/delete-by-id/{id}")
    public JsonResult delete(@PathVariable(value = "id") String id) {
        iProductService.removeById(id);
        return JsonResult.success();
    }

    /**
     * 修改
     */
    @PutMapping("/update")
    public JsonResult updateById(Product product) {
        CommMethod.beanUpdate(product);
        iProductService.updateById(product);
        return JsonResult.success();
    }


    /**
     * 绑定一组传感器，及其对应的阈值
     */
    @PostMapping("/bindSensor")
    public JsonResult bindSensor(@RequestBody BindSensorVo bindSensorVo) {
        Long listenerId = bindSensorVo.getListenerId();
        List<SensorBandVo> sensorBandVoList = bindSensorVo.getSensorBandVo();
        if (listenerId == null || sensorBandVoList == null || sensorBandVoList.isEmpty()) {
            return JsonResult.error();
        }
        Map<Long, List<SensorBandVo>> sensorHandlerBandMap = new HashMap<>();
        for (SensorBandVo sensorBandVo : sensorBandVoList) {
            List<SensorBandVo> list = sensorHandlerBandMap.get(sensorBandVo.getSensorId());
            if (list == null) {
                list = new ArrayList<>();
                sensorHandlerBandMap.put(sensorBandVo.getSensorId(), list);
            }
            list.add(sensorBandVo);
        }
        List<Long> sensorIds = new ArrayList<>(sensorHandlerBandMap.keySet());
        boolean isSuccess = iProductService.bindSensors(listenerId, sensorIds, sensorHandlerBandMap);
        if (isSuccess) {
            return JsonResult.success();
        } else {
            return JsonResult.error();
        }
    }

    @PostMapping("/bindSingleSensor")
    public JsonResult bindSingleSensor(@RequestBody SensorBandVo sensorBandVo) {
        Long listenerId = sensorBandVo.getListenerId();
        Long sensorId = sensorBandVo.getSensorId();
        List<SensorBandVo> sensorBandList = new ArrayList<>();
        sensorBandList.add(sensorBandVo);
        boolean isSuccess = iProductService.bindSensor(listenerId, sensorId, sensorBandList);
        if (isSuccess) {
            return JsonResult.success();
        } else {
            return JsonResult.error();
        }
    }

    /**
     * 绑定传感器列表查询
     */
    @GetMapping("/findAllBindSensor/{listenerId}")
    public JsonResult findAllBindSensor(@PathVariable(value = "listenerId") Long listenerId) {
        return JsonResult.success(iProductService.findAllBindSensor(listenerId));
    }


    /**
     * 解绑一个传感器
     */
    @PostMapping("/unbindSensor")
    public JsonResult unbindSensor(@RequestBody SensorBandVo sensorBandVo) {
        boolean isSuccess = iProductService.unbindSensor(sensorBandVo.getListenerId(), sensorBandVo.getSensorId());
        if (isSuccess) {
            return JsonResult.success();
        } else {
            return JsonResult.error();
        }
    }

    /**
     * 修改一个传感器的阈值
     */
    @PostMapping("/updateListenerBand")
    public JsonResult updateListenerBand(@RequestBody SensorBandVo sensorBandVo) {
        boolean isSuccess = iProductService.updateListenerBand(sensorBandVo.getListenerId(), sensorBandVo.getSensorId(),sensorBandVo.generateListenerBand());
        if (isSuccess) {
            return JsonResult.success();
        } else {
            return JsonResult.error();
        }
    }

    @GetMapping("/findProcList")
    @ApiOperation(value = "查询产品列表")
    public JsonResult findProcList() {
        return JsonResult.success(iProductService.findProcList());
    }
}
