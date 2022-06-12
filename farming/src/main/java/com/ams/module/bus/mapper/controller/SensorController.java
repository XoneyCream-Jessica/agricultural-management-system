package com.ams.module.bus.mapper.controller;

import com.ams.module.bus.domain.entity.sensor.Sensor;
import com.ams.module.bus.entity.Product;
import com.ams.module.bus.service.ISensorService;
import com.ams.util.CommMethod;
import com.ams.util.JsonResult;
import com.ams.util.RedisPager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sensor")
public class SensorController {

    @Autowired
    private ISensorService iSensorService;

    /**
     * 分页查询
     */
    @GetMapping("/findPage")
    public JsonResult findPage(RedisPager<Sensor> pager, Sensor sensor) {
        return JsonResult.success(iSensorService.getSensors(pager, sensor));
    }

    /**
     * 新增
     */
    @PostMapping("/add")
    public JsonResult add(Sensor sensor) {
        iSensorService.createNewSensor(sensor);
        return JsonResult.success();
    }

    /**
     * 通过id删除
     */
    @DeleteMapping("/delete-by-id/{id}")
    public JsonResult delete(@PathVariable(value = "id") Long id) {
        boolean isSuccess = iSensorService.deleteSensor(id);
        if (isSuccess) {
            return JsonResult.success();
        } else {
            return JsonResult.error();
        }
    }

    /**
     * 修改
     */
    @PutMapping("/update")
    public JsonResult updateById(Sensor sensor) {
        sensor = iSensorService.updateSensor(sensor);
        if (sensor != null) {
            return JsonResult.success();
        } else {
            return JsonResult.error();
        }
    }

    /**
     * 列表查询
     */
    @GetMapping("/findList")
    public JsonResult findList(Sensor sensor){
        return JsonResult.success(iSensorService.findList(sensor));
    }

}
