package com.ams.module.bus.domain.vo;


import com.ams.module.bus.broker.EventObject;

public class PestRecognizeEvent extends EventObject {
    private String base64;
    private String predictLabel;
    private String actualLabel;
    private String insecticide;
    public PestRecognizeEvent(long envId, String imgBase64, String predictLabel, String actualLabel) {
        this.setEnvId(envId);
        this.base64 = imgBase64;
        this.predictLabel = predictLabel;
        this.actualLabel = actualLabel;
    }

    public String getBase64() {
        return base64;
    }

    public void setBase64(String base64) {
        this.base64 = base64;
    }

    public String getPredictLabel() {
        return predictLabel;
    }

    public void setPredictLabel(String predictLabel) {
        this.predictLabel = predictLabel;
    }

    public String getActualLabel() {
        return actualLabel;
    }

    public void setActualLabel(String actualLabel) {
        this.actualLabel = actualLabel;
    }

    public String getInsecticide() {
        return insecticide;
    }

    public void setInsecticide(String insecticide) {
        this.insecticide = insecticide;
    }
}
