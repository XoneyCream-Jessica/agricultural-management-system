package com.ams.module.bus.handler;

import com.ams.module.bus.broker.EventBroker;
import com.ams.module.bus.domain.entity.listener.Listener;
import com.ams.module.bus.domain.entity.listener.ListenerBand;
import com.ams.module.bus.domain.enums.HandlerType;
import com.ams.module.bus.domain.repository.IListenerRepository;
import com.ams.module.bus.domain.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TemperatureHandler extends Handler {
    public TemperatureHandler() {
        super(HandlerType.TEMPERATURE);
    }

    @Autowired
    private IListenerRepository iListenerRepository;

    @Override
    public void handle(TemperatureRecognizeEvent event) {
        Double actualTemperature = event.getActualTemperature();//实际湿度
        Listener listener = iListenerRepository.getListenerById(event.getListenerId());
        ListenerBand listenerBand = listener.getListenerBand(event.getSensorId(), handlerType);
        if (listenerBand != null) {
            Double higherBand = listenerBand.getHigherBand();
            Double lowerBand = listenerBand.getLowerBand();
            if (actualTemperature > higherBand) {
                //如果超过了阈值上限，则进行抑制
                InhibitionEvent inhibitionEvent = new InhibitionEvent(event);
                inhibitionEvent.setInhibitionInfo(new TemperatureInhibitionInfo(higherBand));
                EventBroker.post(EventBroker.EventType.getHandlerCenter(), inhibitionEvent);
                EventBroker.post(EventBroker.EventType.getEnvTopic(event.getEnvId()), inhibitionEvent);
            } else if (actualTemperature < lowerBand) {
                //如果低于了阈值下限，则进行促进
                PromotionEvent promotionEvent = new PromotionEvent(event);
                promotionEvent.setPromotionInfo(new TemperaturePromotionInfo(lowerBand));
                EventBroker.post(EventBroker.EventType.getHandlerCenter(), promotionEvent);
                EventBroker.post(EventBroker.EventType.getEnvTopic(event.getEnvId()), promotionEvent);
            }
        }
    }
}
