package com.ams.module.bus.domain.repository;

import com.ams.module.bus.domain.enums.SensorType;
import com.ams.module.bus.domain.entity.env.Environment;

public interface IEnvRepository  extends IRedisRepository<Environment> {
    Environment getEnvBySensorType(SensorType sensorType);
    void updateEnvToRedis(Long envId);
}
