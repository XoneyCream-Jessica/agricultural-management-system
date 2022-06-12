package com.ams.module.bus.domain.repository;


import com.ams.module.bus.domain.entity.sensor.Sensor;

public interface ISensorRepository extends IRedisRepository<Sensor> {
    Sensor getSensorById(long sensorId);

    Sensor createNewSensor(Sensor sensor);

    void deleteSensor(Sensor sensor);

    Sensor updateSensor(Sensor origSensor, Sensor sensor);
}
