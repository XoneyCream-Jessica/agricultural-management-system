package com.ams.module.bus.handler;

import com.ams.module.bus.broker.EventBroker;
import com.ams.module.bus.domain.entity.listener.Listener;
import com.ams.module.bus.domain.entity.listener.ListenerBand;
import com.ams.module.bus.domain.enums.HandlerType;
import com.ams.module.bus.domain.repository.IListenerRepository;
import com.ams.module.bus.domain.vo.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class HumitureHandler extends Handler {
    public HumitureHandler() {
        super(HandlerType.HUMITURE);
    }

    @Autowired
    private IListenerRepository iListenerRepository;


    @Override
    public void handle(HumitureRecognizeEvent event) {
        Double actualHumidity = event.getActualHumidity();//实际湿度
        Listener listener = iListenerRepository.getListenerById(event.getListenerId());
        ListenerBand listenerBand = listener.getListenerBand(event.getSensorId(), handlerType);
        if (listenerBand != null) {
            Double higherBand = listenerBand.getHigherBand();
            Double lowerBand = listenerBand.getLowerBand();
            if (actualHumidity > higherBand) {
                //如果超过了阈值上限，则进行抑制
                InhibitionEvent inhibitionEvent = new InhibitionEvent(event);
                inhibitionEvent.setInhibitionInfo(new HumitureInhibitionInfo(higherBand));
                EventBroker.post(EventBroker.EventType.getHandlerCenter(), inhibitionEvent);
                EventBroker.post(EventBroker.EventType.getEnvTopic(event.getEnvId()), inhibitionEvent);
            } else if (actualHumidity < lowerBand) {
                //如果低于了阈值下限，则进行促进
                PromotionEvent promotionEvent = new PromotionEvent(event);
                promotionEvent.setPromotionInfo(new HumiturePromotionInfo(lowerBand));
                EventBroker.post(EventBroker.EventType.getHandlerCenter(), promotionEvent);
                EventBroker.post(EventBroker.EventType.getEnvTopic(event.getEnvId()), promotionEvent);
            }
        }
    }
}
