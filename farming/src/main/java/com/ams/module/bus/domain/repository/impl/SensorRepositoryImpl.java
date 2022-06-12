package com.ams.module.bus.domain.repository.impl;

import com.ams.abnormal.AmsException;
import com.ams.constant.RedisKey;
import com.ams.module.bus.broker.EventBroker;
import com.ams.module.bus.broker.EventSubscriber;
import com.ams.module.bus.domain.entity.env.Environment;
import com.ams.module.bus.domain.entity.sensor.Sensor;
import com.ams.module.bus.domain.vo.RegisterSensorEvent;
import com.ams.module.bus.domain.vo.UnRegisterSensorEvent;
import com.ams.module.bus.domain.repository.IEnvRepository;
import com.ams.module.bus.domain.repository.ISensorRepository;
import com.ams.redis.RedisCache;
import com.google.common.eventbus.Subscribe;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.*;


@Component
@Slf4j
public class SensorRepositoryImpl implements ISensorRepository, EventSubscriber, ApplicationRunner {

    @Autowired
    private RedisCache redisCache;

    @Autowired
    private IEnvRepository iEnvRepository;

    private Set<Long> registerSensorId = new HashSet<>();

    private final Map<Long, Sensor> sensorMap = new HashMap<>();

    @PostConstruct
    public void init() {
        //注册传感器中心
        EventBroker.register(EventBroker.EventType.getSensorCenter(), this);
        recoverSensorFromRedis();
    }

    /**
     * 监听注册传感器的事件
     */
    @Subscribe
    public void onRegisterSensor(RegisterSensorEvent event) {
        Long sensorId = event.getSensorId();
        long listenerId = event.getListener().getId();
        EventBroker.register(EventBroker.EventType.getSensorTopic(sensorId), event.getListener());
        redisCache.setCacheMapValue(RedisKey.getSensorBindInfoKey(sensorId), String.valueOf(listenerId), sensorId);
        if (!registerSensorId.contains(sensorId)) {
            EventBroker.register(EventBroker.EventType.getSensorTopic(sensorId), this);
            registerSensorId.add(sensorId);
        }
    }

    /**
     * 监听取消注册传感器的事件
     */
    @Subscribe
    public void onUnRegisterSensor(UnRegisterSensorEvent event) {
        long sensorId = event.getSensorId();
        long listenerId = event.getListener().getId();
        EventBroker.unRegister(EventBroker.EventType.getSensorTopic(sensorId), event.getListener());
        redisCache.delCacheMapValue(RedisKey.getSensorBindInfoKey(sensorId), String.valueOf(listenerId));
    }

    public Sensor getSensorById(long sensorId) {
        return sensorMap.get(sensorId);
    }

    /**
     * 创建一个新的传感器，并且往本地和redis中添加信息
     */
    public Sensor createNewSensor(Sensor sensor) {
        try {
            if (sensorMap.containsKey(sensor.getId())) {
                throw new AmsException("duplicate sensor id " + sensor.getId());
            }
            Sensor newSensor = sensor.getSensorType().getSensorClass().getConstructor(Long.class, String.class).newInstance(sensor.getId(), sensor.getName());
            addSensorToLocal(newSensor);
            addSensorToRedis(newSensor);
            registerEnv(sensor);
            return newSensor;
        } catch (Exception e) {
            throw new AmsException("create new sensor failed," + e.getMessage());
        }
    }

    public void deleteSensor(Sensor sensor) {
        sensor = sensorMap.get(sensor.getId());
        if (sensor != null) {
            removeSensorFromLocal(sensor);
            removeSensorFromRedis(sensor);
            unRegisterEnv(sensor);
        }
    }

    private void addSensorToLocal(Sensor sensor) {
        sensorMap.put(sensor.getId(), sensor);
    }

    private void removeSensorFromLocal(Sensor sensor) {
        sensorMap.remove(sensor.getId());
    }

    private void addSensorToRedis(Sensor sensor) {
        redisCache.setCacheObject(RedisKey.getSensorInfoKey(sensor.getId()), sensor);
    }

    private void removeSensorFromRedis(Sensor sensor) {
        redisCache.deleteObject(RedisKey.getSensorInfoKey(sensor.getId()));
    }

    /**
     * 从redis中恢复所有的传感器
     */
    private void recoverSensorFromRedis() {
        Collection<String> keys = redisCache.keys(RedisKey.getSensorInfoKey("*"));
        for (String key : keys) {
            Sensor sensor = redisCache.getCacheObject(key);
            addSensorToLocal(sensor);
        }
    }

    /**
     * 注册环境
     */
    private void registerEnv(Sensor sensor) {
        Environment environment = iEnvRepository.getEnvBySensorType(sensor.getSensorType());
        if (environment != null) {
            sensor.registerEnv(environment.getId());
        } else {
            log.error("can't find {} env", sensor.getSensorType());
        }
    }

    /**
     * 取消注册环境
     */
    private void unRegisterEnv(Sensor sensor) {
        Environment environment = iEnvRepository.getEnvBySensorType(sensor.getSensorType());
        if (environment != null) {
            sensor.unregisterEnv(environment.getId());
        } else {
            log.error("can't find {} env", sensor.getSensorType());
        }
    }

    /**
     * 注册全部环境，在初始化的时候进行
     */
    private void registerAllEnv() {
        for (Sensor sensor : sensorMap.values()) {
            registerEnv(sensor);
        }
    }


    @Override
    public List<Sensor> findList(Sensor param) {
        Collection<Sensor> allSensor = sensorMap.values();
        if (param == null || param.isEmpty()) {
            return new ArrayList<>(allSensor);
        } else {
            List<Sensor> subList = new ArrayList<>();
            for (Sensor sensor : allSensor) {
                if (param.getId() != null && param.getId().equals(sensor.getId())) {
                    subList.add(sensor);
                }
                if (param.getName() != null && param.getName().equals(sensor.getName())) {
                    subList.add(sensor);
                }
            }
            return subList;
        }
    }

    /**
     * 更新传感器，如果传感器类型发生变化，则需要取消注册原先的环境，并且注册新的环境
     */
    public Sensor updateSensor(Sensor origSensor, Sensor newSensor) {
        if (newSensor.getSensorType() != null && !newSensor.getSensorType().equals(origSensor.getSensorType())) {
            unRegisterEnv(origSensor);
            registerEnv(newSensor);
        }
        if (newSensor.getName() == null) {
            newSensor.setName(origSensor.getName());
        }
        addSensorToLocal(newSensor);
        addSensorToRedis(newSensor);
        return newSensor;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        registerAllEnv();
    }
}
