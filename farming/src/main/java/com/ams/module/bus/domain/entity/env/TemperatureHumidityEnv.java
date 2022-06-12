package com.ams.module.bus.domain.entity.env;

import com.ams.module.bus.broker.EventBroker;
import com.ams.module.bus.domain.vo.*;
import com.ams.util.DateUtil;
import com.google.common.eventbus.Subscribe;

import java.util.Calendar;

public class TemperatureHumidityEnv extends Environment {

    public TemperatureHumidityEnv(){

    }

    public TemperatureHumidityEnv(Long id, String name) {
        super(id, name);
        temperature = 20d;
        humidity = 60d;
        isTempPromoting = false;
        isTempInhibiting = false;
        targetTemp = Double.MIN_VALUE;
        tempDuration = -1L;
        isHumidityPromoting = false;
        isHumidityInhibiting = false;
        targetHumidity = Double.MIN_VALUE;
        humidityDuration = -1L;
    }

    private final Object lock = new Object();

    private Double temperature;//温度
    private Double humidity;//湿度

    private Boolean isTempPromoting;
    private Boolean isTempInhibiting;
    private Double targetTemp;
    private Long tempDuration;

    private Boolean isHumidityPromoting;
    private Boolean isHumidityInhibiting;
    private Double targetHumidity;
    private Long humidityDuration;

    private void resetTargetTemp() {
        isTempPromoting = false;
        isTempInhibiting = false;
        targetTemp = Double.MIN_VALUE;
        tempDuration = -1L;
    }

    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    public Double getHumidity() {
        return humidity;
    }

    public void setHumidity(Double humidity) {
        this.humidity = humidity;
    }

    public Boolean getTempPromoting() {
        return isTempPromoting;
    }

    public void setTempPromoting(Boolean tempPromoting) {
        isTempPromoting = tempPromoting;
    }

    public Boolean getTempInhibiting() {
        return isTempInhibiting;
    }

    public void setTempInhibiting(Boolean tempInhibiting) {
        isTempInhibiting = tempInhibiting;
    }

    public Double getTargetTemp() {
        return targetTemp;
    }

    public void setTargetTemp(Double targetTemp) {
        this.targetTemp = targetTemp;
    }

    public Long getTempDuration() {
        return tempDuration;
    }

    public void setTempDuration(Long tempDuration) {
        this.tempDuration = tempDuration;
    }

    public Boolean getHumidityPromoting() {
        return isHumidityPromoting;
    }

    public void setHumidityPromoting(Boolean humidityPromoting) {
        isHumidityPromoting = humidityPromoting;
    }

    public Boolean getHumidityInhibiting() {
        return isHumidityInhibiting;
    }

    public void setHumidityInhibiting(Boolean humidityInhibiting) {
        isHumidityInhibiting = humidityInhibiting;
    }

    public Double getTargetHumidity() {
        return targetHumidity;
    }

    public void setTargetHumidity(Double targetHumidity) {
        this.targetHumidity = targetHumidity;
    }

    public Long getHumidityDuration() {
        return humidityDuration;
    }

    public void setHumidityDuration(Long humidityDuration) {
        this.humidityDuration = humidityDuration;
    }

    private boolean hasTargetTemp() {
        return (isTempPromoting || isTempInhibiting);
    }

    private boolean satisfyTargetTemp(double temperature) {
        if (isTempPromoting) {
            return temperature >= targetTemp;
        }
        if (isTempInhibiting) {
            return temperature <= targetTemp;
        }
        return false;
    }

    private void resetTargetHumidity() {
        isHumidityPromoting = false;
        isHumidityInhibiting = false;
        targetHumidity = Double.MIN_VALUE;
        humidityDuration = -1L;
    }

    private boolean hasTargetHumidity() {
        return (isHumidityPromoting || isHumidityInhibiting);
    }

    private boolean satisfyTargetHumidity(double humidity) {
        if (isHumidityPromoting) {
            return humidity >= targetHumidity;
        }
        if (isHumidityInhibiting) {
            return humidity <= targetHumidity;
        }
        return false;
    }

    @Override
    public void generateData() {
        synchronized (lock) {
            temperature = getTempRandom(temperature);
            EventBroker.post(EventBroker.EventType.getEnvTopic(getId()), new TemperatureRecognizeEvent(getId(), temperature));
            humidity = getHumidityRandom(humidity);
            EventBroker.post(EventBroker.EventType.getEnvTopic(getId()), new HumitureRecognizeEvent(getId(), humidity));
        }
    }

    /*根据时间模拟温度、湿度*/
    private Double getTempRandom(double temperature) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(DateUtil.nowDate());
        int h = cal.get(Calendar.HOUR_OF_DAY);
        double f = Math.random();
        if (isTempPromoting) {
            temperature += 3 * f;
        } else if (isTempInhibiting) {
            temperature -= 3 * f;
        } else if (isHumidityPromoting) {
            //加湿会降温
            temperature -= 2 * f;
        } else {
            if (h >= 6 && h < 15) {
                temperature += f;
            } else {
                temperature -= f;
            }
        }
        if (hasTargetTemp()) {
            tempDuration -= getConfigGenerateInterval();
        }
        if ((tempDuration <= 0 || satisfyTargetTemp(temperature)) && hasTargetTemp()) {
            resetTargetTemp();
        }
        return temperature;
    }

    /*根据时间模拟温度、湿度*/
    private Double getHumidityRandom(double humidity) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(DateUtil.nowDate());
        int h = cal.get(Calendar.HOUR_OF_DAY);
        double f = Math.random();
        if (isHumidityPromoting) {
            humidity += 3 * f;
        } else if (isHumidityInhibiting) {
            humidity -= 3 * f;
        } else {
            if (h >= 6 && h < 15) {
                humidity -= 2 * f;
            } else {
                humidity += f;
            }
        }
        if (hasTargetHumidity()) {
            humidityDuration -= getConfigGenerateInterval();
        }
        if ((humidityDuration <= 0 || satisfyTargetHumidity(humidity)) && hasTargetHumidity()) {
            resetTargetHumidity();
        }
        return humidity;
    }

    /**
     * 采用固定间隔生产数据
     */
    protected long getGenerateInterval() {
        return getConfigGenerateInterval();
    }

    @Subscribe
    public void onPromotionEvent(PromotionEvent event) {
        synchronized (lock) {
            Object info = event.getPromotionInfo();
            if (info instanceof TemperaturePromotionInfo) {
                TemperaturePromotionInfo promotionInfo = (TemperaturePromotionInfo) info;
                if (isTempPromoting) {
                    //如果已经在促进
                    if (targetTemp < promotionInfo.getTargetTemperature()) {
                        //判断当前目标温度是否低于期望的目标问题，如果低于则更新
                        targetTemp = promotionInfo.getTargetTemperature();
                    }
                    tempDuration += promotionInfo.getDuration();
                } else {
                    //如果当前不在促进，则进行促进操作
                    isTempPromoting = true;
                    targetTemp = promotionInfo.getTargetTemperature();
                    tempDuration = promotionInfo.getDuration();
                }
            } else if (info instanceof HumiturePromotionInfo) {
                HumiturePromotionInfo promotionInfo = (HumiturePromotionInfo) info;
                if (isHumidityPromoting) {
                    //如果已经在促进
                    if (targetHumidity < promotionInfo.getTargetHumiture()) {
                        targetHumidity = promotionInfo.getTargetHumiture();
                    }
                    humidityDuration += promotionInfo.getDuration();
                } else {
                    //如果当前不在促进，则进行促进操作
                    isHumidityPromoting = true;
                    targetHumidity = promotionInfo.getTargetHumiture();
                    humidityDuration = promotionInfo.getDuration();
                }
            }
        }
    }

    @Subscribe
    public void onInhibitionEvent(InhibitionEvent event) {
        synchronized (lock) {
            Object info = event.getInhibitionInfo();
            if (info instanceof TemperatureInhibitionInfo) {
                TemperatureInhibitionInfo inhibitionInfo = (TemperatureInhibitionInfo) info;
                if (isTempInhibiting) {
                    //如果已经在促进
                    if (targetTemp > inhibitionInfo.getTargetTemperature()) {
                        //判断当前目标温度是否低于期望的目标问题，如果低于则更新
                        targetTemp = inhibitionInfo.getTargetTemperature();
                    }
                    tempDuration += inhibitionInfo.getDuration();
                } else {
                    //如果当前不在促进，则进行促进操作
                    isTempInhibiting = true;
                    targetTemp = inhibitionInfo.getTargetTemperature();
                    humidityDuration = inhibitionInfo.getDuration();
                }

            } else if (info instanceof HumitureInhibitionInfo) {
                HumitureInhibitionInfo inhibitionInfo = (HumitureInhibitionInfo) info;
                if (isHumidityInhibiting) {
                    //如果已经在促进
                    if (targetHumidity > inhibitionInfo.getTargetHumiture()) {
                        //判断当前目标温度是否低于期望的目标问题，如果低于则更新
                        targetHumidity = inhibitionInfo.getTargetHumiture();
                    }
                    tempDuration += inhibitionInfo.getDuration();
                } else {
                    //如果当前不在促进，则进行促进操作
                    isHumidityInhibiting = true;
                    targetHumidity = inhibitionInfo.getTargetHumiture();
                    humidityDuration = inhibitionInfo.getDuration();
                }
            }
        }
    }
}
