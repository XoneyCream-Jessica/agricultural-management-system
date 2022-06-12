package com.ams.module.bus.vo;

import com.ams.module.bus.domain.entity.listener.Listener;
import com.ams.module.bus.domain.entity.listener.ListenerBand;
import com.ams.module.bus.domain.entity.sensor.Sensor;

public class SensorBandVo {
    private Long listenerId;
    private Long sensorId;
    private String sensorName;
    private Double higherBand;
    private Double lowerBand;

    public SensorBandVo() {

    }

    public SensorBandVo(Double higherBand, Double lowerBand) {
        this.higherBand = higherBand;
        this.lowerBand = lowerBand;
    }

    public SensorBandVo(Listener listener, Sensor sensor, ListenerBand band) {
        listenerId = listener.getId();
        sensorId = sensor.getId();
        sensorName = sensor.getName();
        if (band != null) {
            higherBand = band.getHigherBand();
            lowerBand = band.getLowerBand();
        }
    }

    public Long getSensorId() {
        return sensorId;
    }

    public void setSensorId(Long sensorId) {
        this.sensorId = sensorId;
    }

    public ListenerBand generateListenerBand() {
        return new ListenerBand(higherBand, lowerBand);
    }

    public String getSensorName() {
        return sensorName;
    }

    public void setSensorName(String sensorName) {
        this.sensorName = sensorName;
    }

    public Double getHigherBand() {
        return higherBand;
    }

    public void setHigherBand(Double higherBand) {
        this.higherBand = higherBand;
    }

    public Double getLowerBand() {
        return lowerBand;
    }

    public void setLowerBand(Double lowerBand) {
        this.lowerBand = lowerBand;
    }

    public Long getListenerId() {
        return listenerId;
    }

    public void setListenerId(Long listenerId) {
        this.listenerId = listenerId;
    }
}
