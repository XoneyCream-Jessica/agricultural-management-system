package com.ams.module.bus.domain.vo;

import com.ams.module.bus.broker.EventObject;

/**
 * 促进事件
 */
public class PromotionEvent extends EventObject {

    /**
     * 触发该抑制事件的事件
     */
    private EventObject origEvent;

    private Object promotionInfo;

    public PromotionEvent(EventObject origEvent) {
        this.origEvent = origEvent;
    }

    public EventObject getOrigEvent() {
        return origEvent;
    }

    public void setOrigEvent(EventObject origEvent) {
        this.origEvent = origEvent;
    }

    public Object getPromotionInfo() {
        return promotionInfo;
    }

    public void setPromotionInfo(Object promotionInfo) {
        this.promotionInfo = promotionInfo;

    }
}
