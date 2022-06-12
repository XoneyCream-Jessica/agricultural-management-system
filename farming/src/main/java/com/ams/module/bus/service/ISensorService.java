package com.ams.module.bus.service;

import com.ams.module.bus.domain.entity.sensor.Sensor;
import com.ams.module.bus.domain.enums.SensorType;
import com.ams.util.RedisPager;

import java.util.List;

public interface ISensorService {
    /**
     * 通过ListenerId查询对应的Sensor
     *
     * @param listenerId listenerId,不应为空
     * @param sensorType 传感器类型,为空时则不进行筛选,不为空时会判断sensorType
     * @return 查询到符合条件的sensor列表, 按照id顺序排序
     */
    List<Sensor> getSensorByListenerId(long listenerId, SensorType sensorType);

    RedisPager<Sensor> getSensors(RedisPager<Sensor> pager, Sensor sensor);

    Sensor createNewSensor(Sensor sensor);

    Sensor getSensorById(Long sensorId);

    /**
     * 删除一个传感器,并且解绑listener和传感器
     *
     * @return 如果传感器id不存在, 则会返回false
     */
    boolean deleteSensor(Long sensorId);

    Sensor updateSensor(Sensor sensor);

    List<Sensor> findList(Sensor sensor);
}
