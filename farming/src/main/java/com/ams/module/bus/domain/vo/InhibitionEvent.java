package com.ams.module.bus.domain.vo;

import com.ams.module.bus.broker.EventObject;

/**
 * 抑制事件
 */
public class InhibitionEvent extends EventObject {

    /**
     * 触发该抑制事件的事件
     */
    private EventObject origEvent;

    private Object inhibitionInfo;

    public InhibitionEvent(EventObject origEvent) {
        this.origEvent = origEvent;
    }

    public EventObject getOrigEvent() {
        return origEvent;
    }

    public void setOrigEvent(EventObject origEvent) {
        this.origEvent = origEvent;
    }
    
    public Object getInhibitionInfo() {
        return inhibitionInfo;
    }

    public void setInhibitionInfo(Object inhibitionInfo) {
        this.inhibitionInfo = inhibitionInfo;
    }
}
