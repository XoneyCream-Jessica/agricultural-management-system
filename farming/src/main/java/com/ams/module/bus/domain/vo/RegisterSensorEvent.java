package com.ams.module.bus.domain.vo;

import com.ams.module.bus.broker.EventObject;
import com.ams.module.bus.domain.entity.listener.Listener;

public class RegisterSensorEvent extends EventObject {
    private long sensorId;
    private Listener listener;

    public RegisterSensorEvent( long sensorId, Listener listener) {
        super();
        this.sensorId = sensorId;
        this.listener = listener;
    }

    public long getSensorId() {
        return sensorId;
    }

    public Listener getListener() {
        return listener;
    }
}
