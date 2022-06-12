package com.ams.module.bus.domain.vo;

import com.ams.module.bus.broker.EventObject;

public class TemperatureRecognizeEvent extends EventObject {
    private double actualTemperature;

    public TemperatureRecognizeEvent(long envId, double actualTemperature) {
        this.setEnvId(envId);
        this.actualTemperature = actualTemperature;
    }

    public double getActualTemperature() {
        return actualTemperature;
    }

    public void setActualTemperature(double actualTemperature) {
        this.actualTemperature = actualTemperature;
    }
}
