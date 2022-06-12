package com.ams.module.bus.domain.entity.sensor;

import com.ams.module.bus.broker.EventBroker;
import com.ams.module.bus.domain.enums.SensorType;
import com.ams.module.bus.domain.vo.HumitureRecognizeEvent;
import com.google.common.eventbus.Subscribe;

//温湿度传感器
public class HumitureSensor extends Sensor {
    public HumitureSensor(Long id, String name) {
        super(SensorType.HUMITURE, id, name);
    }

    @Subscribe
    public void onHumidityRecognize(HumitureRecognizeEvent event) {
        event.setSensorId(getId());
        EventBroker.post(EventBroker.EventType.getSensorTopic(getId()), event);
    }

}