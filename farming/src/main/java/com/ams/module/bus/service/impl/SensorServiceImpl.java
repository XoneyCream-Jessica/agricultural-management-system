package com.ams.module.bus.service.impl;

import com.ams.module.bus.domain.entity.BaseEntity;
import com.ams.module.bus.domain.entity.listener.Listener;
import com.ams.module.bus.domain.entity.sensor.Sensor;
import com.ams.module.bus.domain.enums.SensorType;
import com.ams.module.bus.domain.repository.IListenerRepository;
import com.ams.module.bus.domain.repository.ISensorRepository;
import com.ams.module.bus.service.ISensorService;
import com.ams.util.ComparatorUtil;
import com.ams.util.IdUtils;
import com.ams.util.RedisPager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

@Service
public class SensorServiceImpl implements ISensorService {

    @Autowired
    private IListenerRepository iListenerRepository;

    @Autowired
    private ISensorRepository iSensorRepository;

    private static final Comparator<BaseEntity> IdComparator = ComparatorUtil.createIdComparator();

    @Override
    public List<Sensor> getSensorByListenerId(long listenerId, SensorType sensorType) {
        List<Sensor> res = new ArrayList<>();
        Set<Long> sensorIds = iListenerRepository.getSensorIdsByListenerId(listenerId);
        if (sensorIds != null) {
            for (Long sensorId : sensorIds) {
                Sensor sensor = iSensorRepository.getSensorById(sensorId);
                if (sensor != null) {
                    if (sensorType != null && !sensor.getSensorType().equals(sensorType)) {
                        continue;
                    }
                    res.add(sensor);
                }
            }
        }
        res.sort(IdComparator);
        return res;
    }

    @Override
    public RedisPager<Sensor> getSensors(RedisPager<Sensor> pager, Sensor sensor) {
        return iSensorRepository.findPager(pager, sensor);
    }

    @Override
    public Sensor createNewSensor(Sensor sensor) {
        if (sensor.getId() == null) {
            sensor.setId(IdUtils.fastSimpleID());
        }
        return iSensorRepository.createNewSensor(sensor);
    }

    @Override
    public Sensor getSensorById(Long sensorId) {
        return iSensorRepository.getSensorById(sensorId);
    }

    @Override
    public boolean deleteSensor(Long sensorId) {
        Sensor sensor = iSensorRepository.getSensorById(sensorId);
        if (sensor == null) {
            return false;
        }
        Set<Long> listenerIds = iListenerRepository.getListenerIdsBySensorId(sensorId);
        if (listenerIds != null) {
            for (Long listenerId : listenerIds) {
                Listener listener = iListenerRepository.getListenerById(listenerId);
                iListenerRepository.unbindSensor(listener, sensorId);
            }
        }
        iSensorRepository.deleteSensor(sensor);
        return true;
    }


    @Override
    public Sensor updateSensor(Sensor sensor) {
        Sensor origSensor = iSensorRepository.getSensorById(sensor.getId());
        if (origSensor == null) {
            return null;
        }
        sensor = iSensorRepository.updateSensor(origSensor, sensor);
        return sensor;
    }

    @Override
    public List<Sensor> findList(Sensor sensor) {
        return iSensorRepository.findList(sensor);
    }


}
