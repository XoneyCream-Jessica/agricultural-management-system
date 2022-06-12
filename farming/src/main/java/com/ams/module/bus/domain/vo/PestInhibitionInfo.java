package com.ams.module.bus.domain.vo;


public class PestInhibitionInfo {
    private String pesticide;
    private double pesticideAmount;

    public PestInhibitionInfo(String pesticide, double pesticideAmount) {
        this.pesticide = pesticide;
        this.pesticideAmount = pesticideAmount;
    }

    public String getPesticide() {
        return pesticide;
    }

    public void setPesticide(String pesticide) {
        this.pesticide = pesticide;
    }

    public double getPesticideAmount() {
        return pesticideAmount;
    }

    public void setPesticideAmount(double pesticideAmount) {
        this.pesticideAmount = pesticideAmount;
    }
}
