package com.ams.module.bus.domain.entity.env;


import com.ams.module.bus.broker.EventBroker;
import com.ams.module.bus.broker.EventSubscriber;
import com.ams.module.bus.domain.entity.BaseEntity;
import com.ams.util.DateUtil;

import java.util.Random;

/**
 * 环境
 */
public class Environment extends BaseEntity implements Runnable, EventSubscriber {
    public Environment() {

    }

    /**
     * 默认30分钟生成一次数据
     */
    public long generateInterval = DateUtil.calculateMs(30 * 60 * 1000);

    private Random random = new Random();

    public Environment(Long id, String name) {
        super(id, name);
    }


    protected long getConfigGenerateInterval() {
        return generateInterval;
    }

    /**
     * 子类应该实现该方法
     */
    public void generateData() {

    }

    public void init() {
        EventBroker.register(EventBroker.EventType.getHandlerCenter(), this);
    }

    /**
     * 以generateInterval为基准，上下浮动50%
     */
    protected long getGenerateInterval() {
        return getConfigGenerateInterval();
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(getGenerateInterval());
                generateData();
            } catch (Exception e) {
                System.out.println(getClass().getSimpleName() + " exit. " + e.getMessage());
            }
        }
    }

}
