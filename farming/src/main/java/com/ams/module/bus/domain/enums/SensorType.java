package com.ams.module.bus.domain.enums;

import com.ams.module.bus.domain.entity.sensor.HumitureSensor;
import com.ams.module.bus.domain.entity.sensor.PestSensor;
import com.ams.module.bus.domain.entity.sensor.Sensor;
import com.ams.module.bus.domain.entity.sensor.TemperatureSensor;

/**
 * 传感器类型
 */
public enum SensorType {
    PEST_RECOGNITION("PestRecognition", PestSensor.class),
    HUMITURE("Humiture", HumitureSensor.class),
    TEMPERATURE("Temperature", TemperatureSensor.class),
    ;
    private String name;
    private Class<? extends Sensor> sensorClass;

    SensorType(String name, Class<? extends Sensor> sensorClass) {
        this.name = name;
        this.sensorClass = sensorClass;
    }

    public static SensorType findSensorTypeByName(String name) {
        for (SensorType sensorType : values()) {
            if (sensorType.name.equals(name)) {
                return sensorType;
            }
        }
        return null;
    }

    public Class<? extends Sensor> getSensorClass() {
        return sensorClass;
    }

    public String getName() {
        return name;
    }
}
