package com.ams.module.bus.domain.repository.impl;

import com.ams.constant.RedisKey;
import com.ams.module.bus.broker.EventBroker;
import com.ams.module.bus.entity.Product;
import com.ams.module.bus.domain.enums.HandlerType;
import com.ams.module.bus.domain.entity.listener.Listener;
import com.ams.module.bus.domain.entity.listener.ListenerBand;
import com.ams.module.bus.domain.vo.RegisterSensorEvent;
import com.ams.module.bus.domain.vo.UnRegisterSensorEvent;
import com.ams.module.bus.domain.repository.IListenerRepository;
import com.ams.redis.RedisCache;
import com.ams.util.IdUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.*;

/**
 */
@Component
public class ListenerRepositoryImpl implements IListenerRepository, ApplicationRunner {

    @Autowired
    private RedisCache redisCache;

    private final Map<Long, Listener> listenerMap = new HashMap<>();

    /**
     * listener和传感器的对应关系,
     * key是listenerId,value是传感器id的集合
     */
    private final Map<Long, Set<Long>> listenerSensorBindInfo = new HashMap<>();

    /**
     * 传感器和listener的对应关系,
     * key是sensorId,value是listenerId的集合
     */
    private final Map<Long, Set<Long>> sensorListenerBindInfo = new HashMap<>();

    @PostConstruct
    public void init() {
        //恢复所有listener
        recoverAllListenInfoFromRedis();
        //恢复所有bandInfo
        recoverAllListenBandInfoFromRedis();
    }

    /**
     * 从redis恢复所有传感器绑定关系
     */
    private void recoverAllSensorBindInfoFromRedis() {
        Collection<String> keys = redisCache.keys(RedisKey.getSensorBindInfoKey("*"));
        for (String key : keys) {
            Map<String, Long> sensorBindInfo = redisCache.getCacheMap(key);
            for (Map.Entry<String, Long> entry : sensorBindInfo.entrySet()) {
                Long sensorId = entry.getValue();
                Long listenerId = Long.valueOf(entry.getKey());
                Listener listener = getListenerById(listenerId);
                bindSensor(listener, sensorId);
            }
        }
    }

    /**
     * 从redis中恢复所有listener信息
     */
    private void recoverAllListenInfoFromRedis() {
        Collection<String> keys = redisCache.keys(RedisKey.getListenerInfoKey("*"));
        for (String key : keys) {
            Listener listener = redisCache.getCacheObject(key);
            addListenerToLocal(listener);
        }
    }

    /**
     * 从redis中恢复所有监视阈值信息
     */
    private void recoverAllListenBandInfoFromRedis() {
        for (Listener listener : listenerMap.values()) {
            Collection<String> keys = redisCache.keys(RedisKey.getListenerBandInfoKey(listener.getId(), "*"));
            for (String key : keys) {
                long sensorId = Long.valueOf(RedisKey.getLastKeyField(key));
                Map<String, ListenerBand> bandInfoMap = redisCache.getCacheMap(key);
                for (Map.Entry<String, ListenerBand> entry : bandInfoMap.entrySet()) {
                    HandlerType handlerType = HandlerType.findHandlerTypeByName(entry.getKey());
                    listener.addListenBand(sensorId, handlerType, entry.getValue());
                }
            }
        }
    }

    public Listener getListenerById(long listenerId) {
        return listenerMap.get(listenerId);
    }

    /**
     * 绑定传感器
     *
     * @param listener listener
     * @param sensorId 传感器id
     */
    public void bindSensor(Listener listener, long sensorId) {
        Set<Long> sensorIds = listenerSensorBindInfo.get(listener.getId());
        if (sensorIds != null && sensorIds.contains(sensorId)) {
            return;
        }
        addBindToLocal(listener, sensorId);
        //发送注册传感器请求到传感器中心
        EventBroker.post(EventBroker.EventType.getSensorCenter(), new RegisterSensorEvent(sensorId, listener));
    }

    public void unbindSensor(Listener listener, long sensorId) {
        redisCache.deleteObject(RedisKey.getListenerBandInfoKey(listener.getId(), sensorId));
        removeBindToLocal(listener, sensorId);
        EventBroker.post(EventBroker.EventType.getSensorCenter(), new UnRegisterSensorEvent(sensorId, listener));
    }

    /**
     * 添加本地传感器绑定关系
     * 同时往sensorListenerBindInfo（传感器-listener关系表）和 listenerSensorBindInfo（listener-传感器关系表）中添加数据。
     *
     * @param listener listener
     * @param sensorId 传感器id
     */
    private void addBindToLocal(Listener listener, long sensorId) {
        Set<Long> listenerSensorBind = listenerSensorBindInfo.get(listener.getId());
        if (listenerSensorBind == null) {
            listenerSensorBind = new HashSet<>();
            listenerSensorBindInfo.put(listener.getId(), listenerSensorBind);
        }
        listenerSensorBind.add(sensorId);

        Set<Long> sensorListenerBind = sensorListenerBindInfo.get(sensorId);
        if (sensorListenerBind == null) {
            sensorListenerBind = new HashSet<>();
            sensorListenerBindInfo.put(sensorId, sensorListenerBind);
        }
        sensorListenerBind.add(listener.getId());
    }

    /**
     * 删除本地传感器绑定关系
     * 同时删除sensorListenerBindInfo（传感器-listener关系表）和 listenerSensorBindInfo（listener-传感器关系表）中的数据。
     *
     * @param listener listener
     * @param sensorId 传感器id
     */
    private void removeBindToLocal(Listener listener, long sensorId) {
        Set<Long> listenerSensorBind = listenerSensorBindInfo.get(listener.getId());
        if (listenerSensorBind != null) {
            listenerSensorBind.remove(sensorId);
        }
        Set<Long> sensorListenerBind = sensorListenerBindInfo.get(sensorId);
        if (sensorListenerBind != null) {
            sensorListenerBind.remove(listener.getId());
        }
    }

    /**
     * 添加监听阈值
     *
     * @param listener    listener
     * @param sensorId    传感器id
     * @param handlerType 处理器类型
     * @param band        阈值
     */
    public void addBandInfo(Listener listener, long sensorId, HandlerType handlerType, ListenerBand band) {
        redisCache.setCacheMapValue(RedisKey.getListenerBandInfoKey(listener.getId(), sensorId), handlerType.getName(), band);
        listener.addListenBand(sensorId, handlerType, band);
    }

    /**
     * 解绑所有的传感器，需要删除redis和本地的绑定关系
     *
     * @param listener listener
     */
    public void unbindAllSensor(Listener listener) {
        String listenerId = String.valueOf(listener.getId());
        Collection<String> keys = redisCache.keys(RedisKey.getSensorBindInfoKey("*"));
        if (keys == null) {
            return;
        }
        for (String key : keys) {
            Map<String, Long> sensorBindInfo = redisCache.getCacheMap(key);
            for (Map.Entry<String, Long> entry : sensorBindInfo.entrySet()) {
                if (entry.getKey().equals(listenerId)) {
                    unbindSensor(listener, entry.getValue());
                }
            }
        }
    }

    /**
     * 添加listener到本地
     */
    public void addListenerToLocal(Listener listener) {
        listenerMap.put(listener.getId(), listener);
    }

    /**
     * 通过product，创建一个新的listener
     * 并且往redis和本地添加listener信息
     */
    public Listener createNewListener(Product product) {
        Listener listener = new Listener(IdUtils.fastSimpleID(), product.getName());
        redisCache.setCacheObject(RedisKey.getListenerInfoKey(listener.getId()), listener);
        addListenerToLocal(listener);
        return listener;
    }

    public void deleteListener(Listener listener) {
        redisCache.deleteObject(RedisKey.getListenerInfoKey(listener.getId()));
        listenerMap.remove(listener.getId());
        unbindAllSensor(listener);
    }

    public Set<Long> getSensorIdsByListenerId(long listenerId) {
        return listenerSensorBindInfo.get(listenerId);
    }

    public Set<Long> getListenerIdsBySensorId(long sensorId) {
        return sensorListenerBindInfo.get(sensorId);
    }

    @Override
    public List<Listener> findList(Listener param) {
        return new ArrayList<>(listenerMap.values());
    }

    /**
     * 检查监听阈值是否在绑定传感器的关系中，如果不存在删除对应的监听阈值
     */
    @Override
    public void checkAllListenerBand(Long listenerId) {
        Listener listener = listenerMap.get(listenerId);
        checkAllListenerBand(listener);
    }

    @Override
    public void checkAllListenerBand(Listener listener) {
        Map<Long, Map<HandlerType, ListenerBand>> listenerBands = listener.getAllListenerBand();
        if (listenerBands != null) {
            Set<Long> bindSensors = listenerSensorBindInfo.get(listener.getId());
            bindSensors = bindSensors == null ? new HashSet<>() : bindSensors;
            for (Long sensorId : listenerBands.keySet()) {
                if (bindSensors.contains(sensorId)) {
                    listener.delAllListenBand(sensorId);
                    redisCache.deleteObject(RedisKey.getListenerBandInfoKey(listener.getId(), sensorId));
                }
            }
        }
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        recoverAllSensorBindInfoFromRedis();
    }
}
