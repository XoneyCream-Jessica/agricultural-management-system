package com.ams.module.bus.domain.entity.sensor;

import com.ams.module.bus.broker.EventBroker;
import com.ams.module.bus.domain.enums.SensorType;
import com.ams.module.bus.domain.vo.TemperatureRecognizeEvent;
import com.google.common.eventbus.Subscribe;

public class TemperatureSensor extends Sensor {
    public TemperatureSensor(Long id, String name) {
        super(SensorType.TEMPERATURE, id, name);
    }

    @Subscribe
    public void onTemperatureRecognize(TemperatureRecognizeEvent event) {
        event.setSensorId(getId());
        EventBroker.post(EventBroker.EventType.getSensorTopic(getId()), event);
    }
}
