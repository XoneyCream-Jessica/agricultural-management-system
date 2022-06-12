package com.ams.module.bus.service;

import com.ams.module.bus.domain.entity.listener.Listener;
import com.ams.module.bus.domain.entity.listener.ListenerBand;
import com.ams.module.bus.vo.SensorBandVo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ams.module.bus.entity.Product;
import com.ams.util.Pager;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author Auto-generator
 * @since 2022-05-07
 */
public interface IProductService extends IService<Product> {

    Pager<Product> findPage(Pager<Product> pager, Product product);

    List<Product> findList(Product product);

    Product findById(String productId);

    /**
     * 绑定传感器
     *
     * @param listenerId   listenerId
     * @param sensorId     传感器id
     * @param sensorBandVo 可空,如果不为空,则会添加对应的处理阈值
     * @return 是否绑定成功
     */
    boolean bindSensor(Long listenerId, Long sensorId, List<SensorBandVo> sensorBandVo);

    /**
     * 绑定一组传感器
     *
     * @param listenerId           listenerId
     * @param sensorId             传感器id集合
     * @param sensorHandlerBandMap 可空,如果不为空,则会添加对应的处理阈值
     * @return 是否绑定成功
     */
    boolean bindSensors(Long listenerId, List<Long> sensorId, Map<Long, List<SensorBandVo>> sensorHandlerBandMap);

    boolean unbindSensor(Long listenerId, Long sensorId);

    boolean updateListenerBand(Long listenerId, Long sensorId, ListenerBand band);

    List<SensorBandVo> findAllBindSensor(Long listenerId);

    void unbindAllSensor(Long listenerId);

    Product findProductByListenerId(Long listenerId);

    List<Product> findProcList();
}
