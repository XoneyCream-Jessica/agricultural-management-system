package com.ams.module.bus.domain.entity.listener;

import com.ams.module.bus.broker.EventBroker;
import com.ams.module.bus.broker.EventSubscriber;
import com.ams.module.bus.domain.entity.BaseEntity;
import com.ams.module.bus.domain.enums.HandlerType;
import com.ams.module.bus.domain.vo.HumitureRecognizeEvent;
import com.ams.module.bus.domain.vo.PestRecognizeEvent;
import com.ams.module.bus.domain.vo.TemperatureRecognizeEvent;
import com.google.common.eventbus.Subscribe;

import java.util.HashMap;
import java.util.Map;

/**
 * 监听传感器组件
 */
public class Listener extends BaseEntity implements EventSubscriber {

    private final Map<Long, Map<HandlerType, ListenerBand>> listenerBandInfo = new HashMap<>();

    public Listener(Long id, String name) {
        super(id, name);
    }

    public void addListenBand(long sensorId, HandlerType type, ListenerBand band) {
        Map<HandlerType, ListenerBand> listenerBandMap = listenerBandInfo.get(sensorId);
        if (listenerBandMap == null) {
            listenerBandMap = new HashMap<>();
            listenerBandInfo.put(sensorId, listenerBandMap);
        }
        listenerBandMap.put(type, band);
    }

    public void delListenBand(long sensorId, HandlerType handlerType) {
        Map<HandlerType, ListenerBand> listenerBandMap = listenerBandInfo.get(sensorId);
        if (listenerBandMap != null) {
            listenerBandMap.remove(handlerType);
        }
    }

    public void delAllListenBand(long sensorId) {
        listenerBandInfo.remove(sensorId);
    }

    public ListenerBand getListenerBand(long sensorId, HandlerType handlerType) {
        Map<HandlerType, ListenerBand> listenerBandMap = listenerBandInfo.get(sensorId);
        if (listenerBandMap != null) {
            ListenerBand band = listenerBandMap.get(handlerType);
            return band == null ? listenerBandMap.get(HandlerType.DEFAULT) : band;
        }
        return null;
    }

    public Map<Long, Map<HandlerType, ListenerBand>> getAllListenerBand() {
        return listenerBandInfo;
    }

    @Subscribe
    public void onPestRecognize(PestRecognizeEvent event) {
        event.setListenerId(getId());
        EventBroker.post(EventBroker.EventType.getHandlerCenter(), event);
    }

    @Subscribe
    public void onHumidityRecognize(HumitureRecognizeEvent event) {
        event.setListenerId(getId());
        EventBroker.post(EventBroker.EventType.getHandlerCenter(), event);
    }

    @Subscribe
    public void onTemperatureRecognize(TemperatureRecognizeEvent event) {
        event.setListenerId(getId());
        EventBroker.post(EventBroker.EventType.getHandlerCenter(), event);
    }
}
