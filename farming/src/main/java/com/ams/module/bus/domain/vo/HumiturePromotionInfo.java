package com.ams.module.bus.domain.vo;

import com.ams.util.DateUtil;

/**
 * 湿度促进信息
 */
public class HumiturePromotionInfo {
    private Double targetHumiture;
    private static double waterAmount = 10d;
    private static long duration = DateUtil.calculateMs(60 * 60 * 1000);

    public HumiturePromotionInfo(Double targetHumiture) {
        this.targetHumiture = targetHumiture;
    }

    public Double getTargetHumiture() {
        return targetHumiture;
    }

    public void setTargetHumiture(Double targetHumiture) {
        this.targetHumiture = targetHumiture;
    }

    public double getWaterAmount() {
        return waterAmount;
    }

    public long getDuration() {
        return duration;
    }
}
