package com.ams.module.bus.domain.vo;

import com.ams.module.bus.broker.EventObject;

public class HumitureRecognizeEvent extends EventObject {
    private double actualHumidity;

    public HumitureRecognizeEvent(long envId, double actualHumidity) {
        this.setEnvId(envId);
        this.actualHumidity = actualHumidity;
    }

    public double getActualHumidity() {
        return actualHumidity;
    }

    public void setActualHumidity(double actualHumidity) {
        this.actualHumidity = actualHumidity;
    }
}
