package com.ams.module.bus.broker;


import com.google.common.collect.Maps;
import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.EventBus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import java.util.Map;


/**
 * 消息代理
 */
@Component
public class EventBroker {

    private static Map<String, EventBus> eventBusies = Maps.newHashMap();
    private static final Logger logger = LoggerFactory.getLogger(EventBroker.class);
    private static final String MSG_MAIN = "MSG_BUS_";

    private static EventBus newEventBus() {
        return new EventBus();
    }

    public static synchronized void register(String topic, EventSubscriber eventSubscriber) {
        EventBus eventBus = eventBusies.get(topic);
        if (eventBus == null) {
            eventBus = newEventBus();
            eventBusies.put(topic, eventBus);
        }
        logger.info("register topic {}", topic);
        eventBus.register(eventSubscriber);
    }

    public static void post(String topic, EventObject event) {
        EventBus eventBus = eventBusies.get(topic);
        if (eventBus == null) {
            return;
        }
        eventBus.post(event);
    }

    public static void unRegister(String topic, EventSubscriber eventSubscriber) {
        EventBus eventBus = eventBusies.get(topic);
        if (eventBus == null) {
            return;
        }
        eventBus.unregister(eventSubscriber);
        logger.info("unRegister topic {}", topic);
    }


    public static class EventType {
        private static String MSG_BUS_SENSOR = MSG_MAIN + "SENSOR_";
        private static String MSG_BUS_ENV = MSG_MAIN + "ENV_";
        private static String MSG_BUS_SENSOR_CENTER = MSG_BUS_SENSOR + "CENTER";
        private static String MSG_BUS_HANDLER = MSG_MAIN + "HANDLER_";
        private static String MSG_BUS_HANDLER_CENTER = MSG_BUS_HANDLER + "CENTER";
        private static String MSG_BUS_NOTIFY_HANDLER = MSG_MAIN + "NOTIFY";

        public static String getSensorTopic(long sensorId) {
            return MSG_BUS_SENSOR + sensorId;
        }

        public static String getEnvTopic(long envId) {
            return MSG_BUS_ENV + envId;
        }

        public static String getSensorCenter() {
            return MSG_BUS_SENSOR_CENTER;
        }

        public static String getHandlerCenter() {
            return MSG_BUS_HANDLER_CENTER;
        }

        public static String getNotifyHandler() {
            return MSG_BUS_NOTIFY_HANDLER;
        }
    }
}
