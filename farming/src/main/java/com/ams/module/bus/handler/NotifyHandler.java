package com.ams.module.bus.handler;

import com.ams.module.bus.broker.EventBroker;
import com.ams.module.bus.broker.EventSubscriber;
import com.ams.module.bus.domain.entity.listener.Listener;
import com.ams.module.bus.domain.entity.listener.ListenerBand;
import com.ams.module.bus.domain.enums.HandlerType;
import com.ams.module.bus.domain.repository.IListenerRepository;
import com.ams.module.bus.domain.vo.*;
import com.ams.module.bus.entity.Product;
import com.ams.module.bus.service.IProductService;
import com.ams.module.bus.ws.NotifyWebSocketServer;
import com.ams.module.bus.ws.WsMessage;
import com.ams.module.bus.ws.WsMessageType;
import com.ams.util.LanguageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * 通知组件
 */
@Component
public class NotifyHandler extends Handler implements EventSubscriber {

    @Autowired
    private IProductService iProductService;

    @Autowired
    private IListenerRepository iListenerRepository;

    @Autowired
    private NotifyWebSocketServer notifyWebSocketServer;

    public NotifyHandler() {
        super(HandlerType.NOTIFY);
        EventBroker.register(EventBroker.EventType.getNotifyHandler(), this);
    }

    @Override
    public void handle(PestRecognizeEvent event) {
        Product product = iProductService.findProductByListenerId(event.getListenerId());
        if (product == null) {
            return;
        }
        if (super.isLastAcceptEnvEvent(event)) {
            return;
        }
        String predictPest = event.getPredictLabel();
        predictPest = LanguageUtil.get(predictPest);
        notifyWebSocketServer.sendMessage(new WsMessage(WsMessageType.WARNING, "在" + product.getName() + "上检测到" + predictPest));
    }

    @Override
    public void handle(TemperatureRecognizeEvent event) {
        Product product = iProductService.findProductByListenerId(event.getListenerId());
        if (product == null) {
            return;
        }
        if (super.isLastAcceptObject(event)) {
            return;
        }
        Listener listener = iListenerRepository.getListenerById(event.getListenerId());
        ListenerBand listenerBand = listener.getListenerBand(event.getSensorId(), handlerType);
        if (listenerBand != null) {
            Double higherBand = listenerBand.getHigherBand();
            Double lowerBand = listenerBand.getLowerBand();
            Double actualTemperature = event.getActualTemperature();
            if (actualTemperature > higherBand) {
                notifyWebSocketServer.sendMessage(new WsMessage(WsMessageType.WARNING, "检测到当前温度" + String.format("%.2f", actualTemperature) + "℃ 高于" + product.getName() + "设置的阈值" + higherBand + "℃"));
            } else if (actualTemperature < lowerBand) {
                notifyWebSocketServer.sendMessage(new WsMessage(WsMessageType.WARNING, "检测到当前温度" + String.format("%.2f", actualTemperature) + "℃ 低于" + product.getName() + "设置的阈值" + lowerBand + "℃"));
            }
        }
    }

    @Override
    public void handle(HumitureRecognizeEvent event) {
        Product product = iProductService.findProductByListenerId(event.getListenerId());
        if (product == null) {
            return;
        }
        if (super.isLastAcceptObject(event)) {
            return;
        }
        Listener listener = iListenerRepository.getListenerById(event.getListenerId());
        ListenerBand listenerBand = listener.getListenerBand(event.getSensorId(), handlerType);
        if (listenerBand != null) {
            Double higherBand = listenerBand.getHigherBand();
            Double lowerBand = listenerBand.getLowerBand();
            Double actualHumidity = event.getActualHumidity();
            if (actualHumidity > higherBand) {
                notifyWebSocketServer.sendMessage(new WsMessage(WsMessageType.WARNING, "检测到当前湿度" + String.format("%.2f", actualHumidity) + "% 高于" + product.getName() + "设置的阈值" + higherBand + "%"));
            } else if (actualHumidity < lowerBand) {
                notifyWebSocketServer.sendMessage(new WsMessage(WsMessageType.WARNING, "检测到当前湿度" + String.format("%.2f", actualHumidity)
                        + "% 低于" + product.getName() + "设置的阈值" + lowerBand + "%"));
            }
        }
    }

    @Override
    public void handle(PromotionEvent event) {
        if (super.isLastAcceptObject(event.getPromotionInfo())) {
            return;
        }
        notifyWebSocketServer.sendMessage(new WsMessage(WsMessageType.INFO, generateDetailMessage(event)));
    }

    @Override
    public void handle(InhibitionEvent event) {
        if (super.isLastAcceptObject(event.getInhibitionInfo())) {
            return;
        }
        notifyWebSocketServer.sendMessage(new WsMessage(WsMessageType.INFO, generateDetailMessage(event)));
    }

    private String generateDetailMessage(PromotionEvent event) {
        Object promotionInfo = event.getPromotionInfo();
        StringBuilder message = new StringBuilder();
        if (promotionInfo != null) {
            if (promotionInfo instanceof HumiturePromotionInfo) {
                HumiturePromotionInfo info = (HumiturePromotionInfo) promotionInfo;
                message.append("正在进行加湿操作,").append("持续洒水").append(info.getDuration() / 1000).append("秒");
            } else if (promotionInfo instanceof TemperaturePromotionInfo) {
                TemperaturePromotionInfo info = (TemperaturePromotionInfo) promotionInfo;
                message.append("正在进行使用暖风机进行增温");
            }
        }
        return message.toString();
    }

    private String generateDetailMessage(InhibitionEvent event) {
        Object inhibitionInfo = event.getInhibitionInfo();
        StringBuilder message = new StringBuilder();
        if (inhibitionInfo != null) {
            if (inhibitionInfo instanceof HumitureInhibitionInfo) {
                message.append("正在使用除湿机进行除湿操作");
            } else if (inhibitionInfo instanceof TemperatureInhibitionInfo) {
                TemperatureInhibitionInfo info = (TemperatureInhibitionInfo) inhibitionInfo;
                message.append("正在进行通风降温");
            } else if (inhibitionInfo instanceof PestInhibitionInfo) {
                PestInhibitionInfo info = (PestInhibitionInfo) inhibitionInfo;
                message.append("正在准备喷洒").append(String.format("%.1f", info.getPesticideAmount())).append("kg").append(info.getPesticide());
            }
        }
        return message.toString();
    }
}
