package com.ams.module.bus.domain.vo;

import com.ams.util.DateUtil;

public class TemperaturePromotionInfo {
    private Double targetTemperature;
    private static long duration = DateUtil.calculateMs(60 * 60 * 1000);

    public TemperaturePromotionInfo(Double targetTemperature) {
        this.targetTemperature = targetTemperature;
    }

    public long getDuration() {
        return duration;
    }

    public Double getTargetTemperature() {
        return targetTemperature;
    }

    public void setTargetTemperature(Double targetTemperature) {
        this.targetTemperature = targetTemperature;
    }
    
}
