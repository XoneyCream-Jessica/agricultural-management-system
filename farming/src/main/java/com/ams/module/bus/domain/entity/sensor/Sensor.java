package com.ams.module.bus.domain.entity.sensor;

import com.ams.module.bus.broker.EventBroker;
import com.ams.module.bus.broker.EventSubscriber;
import com.ams.module.bus.domain.entity.BaseEntity;
import com.ams.module.bus.domain.enums.SensorType;


/**
 * 传感器
 */
public class Sensor extends BaseEntity implements EventSubscriber, Cloneable {

    protected SensorType sensorType;

    public Sensor() {
        super();
    }

    public Sensor(Long id, String name) {
        super(id, name);
    }

    public Sensor(SensorType sensorType, long id, String name) {
        super(id, name);
        this.sensorType = sensorType;
    }

    public void registerEnv(long envId) {
        //订阅环境变化情况
        EventBroker.register(EventBroker.EventType.getEnvTopic(envId), this);
    }

    public void unregisterEnv(long envId) {
        //取消订阅环境变化情况
        EventBroker.unRegister(EventBroker.EventType.getEnvTopic(envId), this);
    }

    public SensorType getSensorType() {
        return sensorType;
    }

    public void setSensorType(SensorType sensorType) {
        this.sensorType = sensorType;
    }

    @Override
    public boolean isEmpty() {
        return super.isEmpty() && sensorType == null;
    }

}
