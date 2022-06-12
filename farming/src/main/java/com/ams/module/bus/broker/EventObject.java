package com.ams.module.bus.broker;

import com.ams.util.DateUtil;
import com.ams.util.IdUtils;

public abstract class EventObject {
    private final long currentTime;
    private long id;
    private long envId;
    private long sensorId;
    private long listenerId;

    public EventObject() {
        this.id = IdUtils.fastSimpleID();
        currentTime = DateUtil.getTimestamp();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getCurrentTime() {
        return currentTime;
    }

    public long getEnvId() {
        return envId;
    }

    public void setEnvId(long envId) {
        this.envId = envId;
    }

    public long getSensorId() {
        return sensorId;
    }

    public void setSensorId(long sensorId) {
        this.sensorId = sensorId;
    }

    public long getListenerId() {
        return listenerId;
    }

    public void setListenerId(long listenerId) {
        this.listenerId = listenerId;
    }

}
