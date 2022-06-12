package com.ams.module.bus.domain.repository;

import com.ams.module.bus.domain.entity.listener.ListenerBand;
import com.ams.module.bus.domain.enums.HandlerType;
import com.ams.module.bus.entity.Product;
import com.ams.module.bus.domain.entity.listener.Listener;

import java.util.Set;

public interface IListenerRepository extends IRedisRepository<Listener> {
    Listener getListenerById(long listenerId);

    Listener createNewListener(Product product);

    Set<Long> getSensorIdsByListenerId(long listenerId);

    Set<Long> getListenerIdsBySensorId(long sensorId);

    void bindSensor(Listener listener, long sensorId);

    void unbindSensor(Listener listener, long sensorId);

    void addBandInfo(Listener listener, long sensorId, HandlerType handlerType, ListenerBand band);

    void unbindAllSensor(Listener listener);

    void checkAllListenerBand(Long listenerId);

    void deleteListener(Listener listener);

    void checkAllListenerBand(Listener listener);
}
