package com.ams.module.bus.vo;

import java.util.List;

public class BindSensorVo {
    private Long listenerId;
    private List<SensorBandVo> sensorBandVo;

    public Long getListenerId() {
        return listenerId;
    }

    public void setListenerId(Long listenerId) {
        this.listenerId = listenerId;
    }

    public List<SensorBandVo> getSensorBandVo() {
        return sensorBandVo;
    }

    public void setSensorBandVo(List<SensorBandVo> sensorBandVo) {
        this.sensorBandVo = sensorBandVo;
    }
}
