package com.ams.module.bus.handler;

import com.ams.module.bus.broker.EventBroker;
import com.ams.module.bus.broker.EventSubscriber;
import com.ams.module.bus.domain.vo.*;

import com.google.common.eventbus.Subscribe;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;


@Component
@Slf4j
public class HandlerCenter implements EventSubscriber {

    private final List<Handler> handlerChain = new ArrayList<>();

    @Autowired
    private NotifyHandler notifyHandler;

    @Autowired
    private PestKillerHandler pestKillerHandler;

    @Autowired
    private HumitureHandler humitureHandler;

    @Autowired
    private TemperatureHandler temperatureHandler;

    @Autowired
    private PersistHandler persistHandler;

    @PostConstruct
    public void init() {
        EventBroker.register(EventBroker.EventType.getHandlerCenter(), this);
        handlerChain.add(notifyHandler);
        handlerChain.add(pestKillerHandler);
        handlerChain.add(humitureHandler);
        handlerChain.add(temperatureHandler);

        //持久化处理器应该放在事件流的后方，防止之前的处理器对event做修改
        handlerChain.add(persistHandler);
    }

    @Subscribe
    public void onPestRecognizeEvent(PestRecognizeEvent event) {
        for (Handler handler : handlerChain) {
            try {
                handler.handle(event);
            } catch (Exception e) {
                log.error("{} handle {} error {}", handler.handlerType, event.getClass().getSimpleName(), e.getMessage());
            }
        }
    }


    @Subscribe
    public void onHumitureRecognizeEvent(HumitureRecognizeEvent event) {
        for (Handler handler : handlerChain) {
            try {
                handler.handle(event);
            } catch (Exception e) {
                log.error("{} handle {} error {}", handler.handlerType, event.getClass().getSimpleName(), e.getMessage());
            }
        }
    }

    @Subscribe
    public void onTemperatureRecognizeEvent(TemperatureRecognizeEvent event) {
        for (Handler handler : handlerChain) {
            try {
                handler.handle(event);
            } catch (Exception e) {
                log.error("{} handle {} error {}", handler.handlerType, event.getClass().getSimpleName(), e.getMessage());
            }
        }
    }

    @Subscribe
    public void onPromotionEvent(PromotionEvent event) {
        for (Handler handler : handlerChain) {
            try {
                handler.handle(event);
            } catch (Exception e) {
                log.error("{} handle {} error {}", handler.handlerType, event.getClass().getSimpleName(), e.getMessage());
            }
        }
    }

    @Subscribe
    public void onInhibitionEvent(InhibitionEvent event) {
        for (Handler handler : handlerChain) {
            try {
                handler.handle(event);
            } catch (Exception e) {
                log.error("{} handle {} error {}", handler.handlerType, event.getClass().getSimpleName(), e.getMessage());
            }
        }
    }
}
