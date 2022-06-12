package com.ams.module.bus.domain.entity.sensor;

import com.ams.module.bus.broker.EventBroker;
import com.ams.module.bus.domain.enums.SensorType;
import com.ams.module.bus.domain.vo.PestRecognizeEvent;
import com.google.common.eventbus.Subscribe;

public class PestSensor extends Sensor {

    public PestSensor(Long id, String name) {
        super(SensorType.PEST_RECOGNITION, id, name);
    }

    @Subscribe
    public void onPestRecognize(PestRecognizeEvent event) {
        //do nothing, only update sensor id
        event.setSensorId(getId());
        EventBroker.post(EventBroker.EventType.getSensorTopic(getId()), event);
    }
}
