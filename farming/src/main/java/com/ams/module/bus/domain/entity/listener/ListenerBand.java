package com.ams.module.bus.domain.entity.listener;

import com.ams.module.bus.domain.entity.BaseEntity;
import com.ams.util.IdUtils;

public class ListenerBand extends BaseEntity {
    private Double higherBand;
    private Double lowerBand;

    public ListenerBand() {
        super(IdUtils.fastSimpleID(), "");
    }

    public ListenerBand(Long id, String name) {
        super(id, name);
    }

    public ListenerBand(Double higherBand, Double lowerBand) {
        super(IdUtils.fastSimpleID(), "");
        this.higherBand = higherBand;
        this.lowerBand = lowerBand;
    }

    public Double getLowerBand() {
        return lowerBand;
    }

    public void setLowerBand(Double lowerBand) {
        this.lowerBand = lowerBand;
    }

    public Double getHigherBand() {
        return higherBand;
    }

    public void setHigherBand(Double higherBand) {
        this.higherBand = higherBand;
    }

    public boolean isHigherThanBand(double value) {
        return value > higherBand;
    }

    public boolean isLowerThanBand(double value) {
        return value < lowerBand;
    }

}
