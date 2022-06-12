package com.ams.module.bus.handler;

import com.ams.module.bus.broker.EventObject;
import com.ams.module.bus.domain.enums.HandlerType;
import com.ams.module.bus.domain.vo.*;

import java.util.HashMap;
import java.util.Map;

/**
 * 事件处理器
 */
public abstract class Handler {
    protected final HandlerType handlerType;

    public Handler(HandlerType handlerType) {
        this.handlerType = handlerType;
    }

    public void handle(PestRecognizeEvent event) {
        //doNothing
    }

    public void handle(HumitureRecognizeEvent event) {

    }

    public void handle(TemperatureRecognizeEvent event) {

    }

    public void handle(PromotionEvent event) {

    }

    public void handle(InhibitionEvent event) {

    }

    private Map<Long, Long> envEventHistory = new HashMap<>();

    private Map<Class<?>, Long> objectHistory = new HashMap<>();

    private final long notifyThreshold = 30 * 1000;

    protected final static String sysUserId = "1c3a5eb4ccfe3d636743ae56e2286f3b";

    protected final static Long growingInterval = 30 * 60 * 1000L;


    boolean isLastAcceptEnvEvent(EventObject event, long interval) {
        Long lastEventTimestamp = envEventHistory.get(event.getEnvId());
        //这里使用真实时间判断
        Long currentTime = System.currentTimeMillis();
        if (lastEventTimestamp != null && (currentTime - lastEventTimestamp) < interval) {
            return true;
        }
        envEventHistory.put(event.getEnvId(), currentTime);
        return false;
    }

    /**
     * 最近是否处理过env的事件
     */
    boolean isLastAcceptEnvEvent(EventObject event) {
        return isLastAcceptEnvEvent(event, notifyThreshold);
    }

    boolean isLastAcceptObject(Object event) {
        if (event == null) {
            return false;
        }
        Long lastEventTimestamp = objectHistory.get(event.getClass());
        //这里使用真实时间判断
        Long currentTime = System.currentTimeMillis();
        if (lastEventTimestamp != null && (currentTime - lastEventTimestamp) < notifyThreshold) {
            return true;
        }
        objectHistory.put(event.getClass(), currentTime);
        return false;
    }
}
