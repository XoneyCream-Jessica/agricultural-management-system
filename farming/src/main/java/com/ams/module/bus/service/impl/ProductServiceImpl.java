package com.ams.module.bus.service.impl;

import cn.hutool.core.util.StrUtil;
import com.ams.module.bus.domain.entity.listener.Listener;
import com.ams.module.bus.domain.entity.listener.ListenerBand;
import com.ams.module.bus.domain.entity.sensor.Sensor;
import com.ams.module.bus.domain.enums.HandlerType;
import com.ams.module.bus.domain.repository.IListenerRepository;
import com.ams.module.bus.domain.repository.ISensorRepository;
import com.ams.module.bus.vo.SensorBandVo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ams.constant.BusConstant;
import com.ams.module.bus.entity.Product;
import com.ams.module.bus.mapper.ProductMapper;
import com.ams.module.bus.service.IProductService;
import com.ams.util.Pager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.*;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Auto-generator
 * @since 2022-05-07
 */
@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements IProductService {

    @Autowired
    private IListenerRepository iListenerRepository;

    @Autowired
    private ISensorRepository iSensorRepository;

    @Override
    public Pager<Product> findPage(Pager<Product> pager, Product product) {
        QueryWrapper<Product> qw = new QueryWrapper<>();
        if (StrUtil.isNotEmpty(product.getName())) {
            qw.like("tab.name", product.getName());
        }
        if (StrUtil.isNotEmpty(product.getType())) {
            qw.eq("tab.type", product.getType());
        }
        qw.eq("tab.del_flag", BusConstant.DEL_FLAG_N);
        return baseMapper.findPage(pager, qw);
    }

    @Override
    public List<Product> findList(Product product) {
        QueryWrapper<Product> qw = new QueryWrapper<>();
        if (StrUtil.isNotEmpty(product.getName())) {
            qw.like("name", product.getName());
        }
        if (StrUtil.isNotEmpty(product.getType())) {
            qw.eq("type", product.getType());
        }
        return baseMapper.selectList(qw);
    }

    @Override
    public Product findProductByListenerId(Long listenerId) {
        QueryWrapper<Product> qw = new QueryWrapper<>();
        qw.eq("listener_id", listenerId);
        return baseMapper.selectOne(qw);
    }

    @Override
    public Product findById(String productId) {
        return baseMapper.findById(productId);
    }

    @Override
    public boolean save(Product product) {
        createNewListener(product);
        return super.save(product);
    }

    @Override
    public boolean updateById(Product product) {
        //如果listenerId为初始值
        Product origProduct = findById(product.getId());
        if (origProduct.getListenerId() == null || origProduct.getListenerId() == -1) {
            origProduct.setName(product.getName());
            origProduct = createNewListener(origProduct);
            product.setListenerId(origProduct.getListenerId());
        }
        return super.updateById(product);
    }


    private Product createNewListener(Product product) {
        Listener listener = iListenerRepository.createNewListener(product);
        product.setListenerId(listener.getId());
        return product;
    }

    @Override
    public boolean bindSensor(Long listenerId, Long sensorId, List<SensorBandVo> sensorBandVo) {
        boolean res = false;
        Listener listener = iListenerRepository.getListenerById(listenerId);
        Sensor sensor = iSensorRepository.getSensorById(sensorId);
        if (listener != null && sensor != null) {
            iListenerRepository.bindSensor(listener, sensorId);
            if (sensorBandVo != null) {
                for (SensorBandVo band : sensorBandVo) {
                    iListenerRepository.addBandInfo(listener, sensorId, HandlerType.DEFAULT, band.generateListenerBand());
                }
            }
            res = true;
        }
        return res;
    }

    @Override
    public boolean bindSensors(Long listenerId, List<Long> sensorIds, Map<Long, List<SensorBandVo>> sensorHandlerBandMap) {
        unbindAllSensor(listenerId);
        boolean isSuccess;
        sensorHandlerBandMap = sensorHandlerBandMap == null ? new HashMap<>() : sensorHandlerBandMap;
        for (Long sensorId : sensorIds) {
            isSuccess = bindSensor(listenerId, sensorId, sensorHandlerBandMap.get(sensorId));
            if (!isSuccess) {
                return false;
            }
        }
        iListenerRepository.checkAllListenerBand(listenerId);
        return true;
    }

    @Override
    public boolean unbindSensor(Long listenerId, Long sensorId) {
        Listener listener = iListenerRepository.getListenerById(listenerId);
        if (listener != null) {
            iListenerRepository.unbindSensor(listener, sensorId);
            return true;
        }
        return false;
    }

    @Override
    public boolean updateListenerBand(Long listenerId, Long sensorId, ListenerBand band) {
        Listener listener = iListenerRepository.getListenerById(listenerId);
        if (listener != null) {
            iListenerRepository.addBandInfo(listener, sensorId, HandlerType.DEFAULT, band);
            return true;
        }
        return false;
    }


    @Override
    public List<SensorBandVo> findAllBindSensor(Long listenerId) {
        List<SensorBandVo> res = new ArrayList<>();
        if (listenerId == null) {
            return res;
        }
        Listener listener = iListenerRepository.getListenerById(listenerId);
        if (listener == null) {
            return res;
        }
        Set<Long> sensorIds = iListenerRepository.getSensorIdsByListenerId(listenerId);
        if (sensorIds != null && !sensorIds.isEmpty()) {
            for (Long sensorId : sensorIds) {
                Sensor sensor = iSensorRepository.getSensorById(sensorId);
                ListenerBand listenerBand = listener.getListenerBand(sensorId, HandlerType.DEFAULT);
                res.add(new SensorBandVo(listener, sensor, listenerBand));
            }
        }
        return res;
    }

    @Override
    public void unbindAllSensor(Long listenerId) {
        if (listenerId == null) {
            return;
        }
        Listener listener = iListenerRepository.getListenerById(listenerId);
        if (listener != null) {
            iListenerRepository.unbindAllSensor(listener);
        }
    }

    @Override
    public boolean removeById(Serializable id) {
        Product origProduct = baseMapper.selectById(id);
        Listener listener = iListenerRepository.getListenerById(origProduct.getListenerId());
        iListenerRepository.deleteListener(listener);
        iListenerRepository.checkAllListenerBand(listener);
        return super.removeById(id);
    }

    @Override
    public List<Product> findProcList() {
        return baseMapper.findProcList();
    }
}
