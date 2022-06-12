package com.ams.module.bus.domain.vo;

import com.ams.util.DateUtil;

public class HumitureInhibitionInfo {
    private Double targetHumiture;
    private static long duration = DateUtil.calculateMs(60 * 60 * 1000);

    public HumitureInhibitionInfo(Double targetHumiture) {
        this.targetHumiture = targetHumiture;
    }

    public Double getTargetHumiture() {
        return targetHumiture;
    }

    public void setTargetHumiture(Double targetHumiture) {
        this.targetHumiture = targetHumiture;
    }

    public long getDuration() {
        return duration;
    }
}
