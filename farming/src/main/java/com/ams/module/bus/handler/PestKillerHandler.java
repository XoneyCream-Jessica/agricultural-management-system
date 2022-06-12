package com.ams.module.bus.handler;

import com.ams.module.bus.broker.EventBroker;
import com.ams.module.bus.domain.enums.HandlerType;
import com.ams.module.bus.domain.vo.InhibitionEvent;
import com.ams.module.bus.domain.vo.PestInhibitionInfo;
import com.ams.module.bus.domain.vo.PestRecognizeEvent;
import com.ams.util.PestUtil;
import org.springframework.stereotype.Component;

@Component
public class PestKillerHandler extends Handler {

    public PestKillerHandler() {
        super(HandlerType.PESTKILLER);
    }

    public void handle(PestRecognizeEvent event) {
        if (isLastAcceptEnvEvent(event, 2 * 1000)) {
            return;
        }
        String pest = event.getPredictLabel();
        String pesticide = PestUtil.getPesticideByPest(pest);
        if (pesticide != null) {
            InhibitionEvent inhibitionEvent = new InhibitionEvent(event);
            inhibitionEvent.setInhibitionInfo(new PestInhibitionInfo(pesticide, (3 + Math.random())));
            EventBroker.post(EventBroker.EventType.getHandlerCenter(), inhibitionEvent);
            EventBroker.post(EventBroker.EventType.getEnvTopic(event.getEnvId()), inhibitionEvent);
        }
    }
}
