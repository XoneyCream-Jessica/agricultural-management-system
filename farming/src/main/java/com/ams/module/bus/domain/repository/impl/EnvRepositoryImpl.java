package com.ams.module.bus.domain.repository.impl;

import com.ams.abnormal.AmsException;
import com.ams.constant.RedisKey;
import com.ams.module.bus.domain.entity.env.Environment;
import com.ams.module.bus.domain.entity.env.PestEnv;
import com.ams.module.bus.domain.entity.env.TemperatureHumidityEnv;
import com.ams.module.bus.domain.enums.SensorType;
import com.ams.module.bus.domain.repository.IEnvRepository;
import com.ams.redis.RedisCache;
import com.ams.util.IdUtils;
import lombok.extern.slf4j.Slf4j;
import org.reflections.Reflections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.*;

@Component
@Slf4j
public class EnvRepositoryImpl implements IEnvRepository {

    private final static Map<Class, List<SensorType>> envSensorConverter = new HashMap<>();

    static {
        envSensorConverter.put(PestEnv.class, new ArrayList<SensorType>() {
            {
                add(SensorType.PEST_RECOGNITION);
            }
        });
        envSensorConverter.put(TemperatureHumidityEnv.class, new ArrayList<SensorType>() {
            {
                add(SensorType.TEMPERATURE);
                add(SensorType.HUMITURE);
            }
        });
    }

    private final Map<SensorType, Environment> envSensorMap = new HashMap<>();

    private final Map<Long, Environment> envIdMap = new HashMap<>();

    @Autowired
    private RedisCache redisCache;

    @Autowired
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;

    @PostConstruct
    public void init() {
        //恢复所有Env
        recoverAllEnvInfoFromRedis();
        //生成默认env
        generateDefaultEnv();
        //run all env
        executeAllEnv();
    }

    public Environment getEnvBySensorType(SensorType sensorType) {
        return envSensorMap.get(sensorType);
    }

    @Override
    public void updateEnvToRedis(Long envId) {
        Environment environment = envIdMap.get(envId);
        if (environment != null) {
            updateEnvToRedis(environment);
        }
    }

    public Environment getEnvById(long envId) {
        return envIdMap.get(envId);
    }

    private void recoverAllEnvInfoFromRedis() {
        Collection<String> keys = redisCache.keys(RedisKey.getEnvInfoKey("*"));
        for (String key : keys) {
            try {
                Environment env = redisCache.getCacheObject(key);
                if (env != null) {
                    addEnvToLocal(env);
                    addEnvSensorMap(env);
                    env.init();
                } else {
                    //创建env失败
                    redisCache.deleteObject(key);
                }
            } catch (Exception e) {
                log.error("recover env error {}", e.getMessage());
                redisCache.deleteObject(key);
            }
        }
    }

    private void addEnvToLocal(Environment environment) {
        envIdMap.put(environment.getId(), environment);
    }

    private void addEnvSensorMap(Environment environment) {
        for (Environment env : envSensorMap.values()) {
            if (env.getId().equals(environment.getId())) {
                return;
            }
        }
        List<SensorType> sensorTypes = getSensorTypeByEnv(environment);
        for (SensorType sensorType : sensorTypes) {
            envSensorMap.put(sensorType, environment);
        }
    }

    private List<SensorType> getSensorTypeByEnv(Environment environment) {
        return envSensorConverter.get(environment.getClass());
    }

    /**
     * 更新redis的环境信息
     */
    public void updateEnvToRedis(Environment environment) {
        redisCache.setCacheObject(RedisKey.getEnvInfoKey(environment.getId()), environment);
    }

    /**
     * 通过反射，获取所有继承Environment的env类，如果env还没有创建出来，则调用构造方法进行创建。
     * 并且添加到本地和redis中，同时更新传感器类型和env的对应关系
     */
    private void generateDefaultEnv() {
        Reflections f = new Reflections(Environment.class.getPackage().getName());
        Set<Class<? extends Environment>> subEnvs = f.getSubTypesOf(Environment.class);
        try {
            for (Class<? extends Environment> subEnv : subEnvs) {
                boolean exists = false;
                for (Environment existEnv : envIdMap.values()) {
                    if (existEnv.getClass().equals(subEnv)) {
                        exists = true;
                        break;
                    }
                }
                if (!exists) {
                    Environment env = subEnv.getConstructor(Long.class, String.class).newInstance(IdUtils.fastSimpleID(), subEnv.getSimpleName());
                    addEnvToLocal(env);
                    addEnvSensorMap(env);
                    updateEnvToRedis(env);
                }
            }
        } catch (Exception e) {
            throw new AmsException("generateDefaultEnv error " + e.getMessage());
        }
    }

    private void executeAllEnv() {
        for (Environment environment : envIdMap.values()) {
            threadPoolTaskExecutor.execute(environment);
        }
    }

    @Override
    public List<Environment> findList(Environment param) {
        Collection<Environment> allEnv = envIdMap.values();
        if (param == null || param.isEmpty()) {
            return new ArrayList<>(allEnv);
        } else {
            List<Environment> subList = new ArrayList<>();
            for (Environment environment : allEnv) {
                if (param.getId() != null && param.getId().equals(environment.getId())) {
                    subList.add(environment);
                }
                if (param.getName() != null && param.getName().equals(environment.getName())) {
                    subList.add(environment);
                }
            }
            return subList;
        }
    }
}
