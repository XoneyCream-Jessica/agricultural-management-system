package com.ams.module.bus.handler;


import com.ams.constant.BusConstant;
import com.ams.module.bus.broker.EventObject;
import com.ams.module.bus.domain.enums.HandlerType;
import com.ams.module.bus.domain.repository.IEnvRepository;
import com.ams.module.bus.domain.vo.*;
import com.ams.module.bus.entity.GrowingEnvironment;
import com.ams.module.bus.entity.Product;
import com.ams.module.bus.service.IGrowingEnvironmentService;
import com.ams.module.bus.service.IProductService;
import com.ams.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * 处理事件持久化
 */
@Component
public class PersistHandler extends Handler {
    public PersistHandler() {
        super(HandlerType.PERSIST);
    }

    @Autowired
    private IProductService iProductService;

    @Autowired
    private IGrowingEnvironmentService iGrowingEnvironmentService;

    @Autowired
    private IEnvRepository iEnvRepository;

    @Override
    public void handle(TemperatureRecognizeEvent event) {
        super.handle(event);
        GrowingEnvironment lastGrowEnv = getLastGrowingEnv(event);
        if (lastGrowEnv == null) {
            return;
        }
        lastGrowEnv.setTemperature(new BigDecimal(event.getActualTemperature()));
        iGrowingEnvironmentService.saveOrUpdate(lastGrowEnv);
        iEnvRepository.updateEnvToRedis(event.getEnvId());
    }

    @Override
    public void handle(PestRecognizeEvent event) {
        super.handle(event);
        GrowingEnvironment lastGrowEnv = getLastGrowingEnv(event);
        if (lastGrowEnv == null) {
            return;
        }
        lastGrowEnv.setPestName(event.getPredictLabel());
        iGrowingEnvironmentService.saveOrUpdate(lastGrowEnv);
        iEnvRepository.updateEnvToRedis(event.getEnvId());
    }


    @Override
    public void handle(HumitureRecognizeEvent event) {
        super.handle(event);
        GrowingEnvironment lastGrowEnv = getLastGrowingEnv(event);
        if (lastGrowEnv == null) {
            return;
        }
        lastGrowEnv.setHumidity(new BigDecimal(event.getActualHumidity()));
        iGrowingEnvironmentService.saveOrUpdate(lastGrowEnv);
        iEnvRepository.updateEnvToRedis(event.getEnvId());
    }

    @Override
    public void handle(PromotionEvent event) {
        iEnvRepository.updateEnvToRedis(event.getOrigEvent().getEnvId());
        if (super.isLastAcceptEnvEvent(event)) {
            return;
        }
        Object info = event.getPromotionInfo();
        if (info != null) {
            if (info instanceof HumiturePromotionInfo) {
                HumiturePromotionInfo promotionInfo = (HumiturePromotionInfo) info;
                GrowingEnvironment lastGrowEnv = getLastGrowingEnv(event.getOrigEvent());
                if (lastGrowEnv == null) {
                    return;
                }
                BigDecimal waterAmount = lastGrowEnv.getWaterAmount();
                if (waterAmount == null) {
                    waterAmount = new BigDecimal(0);
                }
                lastGrowEnv.setWaterAmount(waterAmount.add(new BigDecimal(promotionInfo.getWaterAmount())));
                iGrowingEnvironmentService.saveOrUpdate(lastGrowEnv);
            }
        }
    }

    @Override
    public void handle(InhibitionEvent event) {
        iEnvRepository.updateEnvToRedis(event.getOrigEvent().getEnvId());
        Object info = event.getInhibitionInfo();
        if (info != null) {
            if (info instanceof PestInhibitionInfo) {
                PestInhibitionInfo pestInhibitionInfo = (PestInhibitionInfo) info;
                GrowingEnvironment lastGrowEnv = getLastGrowingEnv(event.getOrigEvent());
                if (lastGrowEnv == null) {
                    return;
                }
                BigDecimal pesticideAmount = lastGrowEnv.getPesticideAmount();
                if (pesticideAmount == null) {
                    pesticideAmount = new BigDecimal(0);
                }
                lastGrowEnv.setPesticideAmount(pesticideAmount.add(new BigDecimal(pestInhibitionInfo.getPesticideAmount())));
                lastGrowEnv.setPesticideName(pestInhibitionInfo.getPesticide());
                lastGrowEnv.setIsPesticide("Y");
                iGrowingEnvironmentService.saveOrUpdate(lastGrowEnv);
            }
        }
    }

    /**
     * 获取最新一笔生长记录信息，
     * 如果最新一笔与当前时间超过growingInterval，则创建一笔新的
     */
    private GrowingEnvironment getLastGrowingEnv(EventObject event) {
        Long now = DateUtil.getTimestamp();
        Product product = iProductService.findProductByListenerId(event.getListenerId());
        if (product != null) {
            GrowingEnvironment lastGrowEnv = iGrowingEnvironmentService.findLastGrowEnv(new GrowingEnvironment(product.getId()));
            if (lastGrowEnv != null && lastGrowEnv.getCollectDate() != null) {
                long interval = now - DateUtil.localDateTimeToTimestamp(lastGrowEnv.getCollectDate());
                if (interval > growingInterval) {
                    lastGrowEnv = null;
                }
            }
            if (lastGrowEnv != null) {
                lastGrowEnv.setUpdateUser(sysUserId);
                lastGrowEnv.setUpdateDate(DateUtil.nowLocalDateTime());
                if (lastGrowEnv.getCollectDate() == null) {
                    lastGrowEnv.setCollectDate(lastGrowEnv.getCreateDate());
                }
                lastGrowEnv.setExist(true);
            } else {
                lastGrowEnv = new GrowingEnvironment(product.getId());
                lastGrowEnv.setIsPesticide("N");
                lastGrowEnv.setCreateDate(DateUtil.nowLocalDateTime());
                lastGrowEnv.setCreateUser(sysUserId);
                lastGrowEnv.setDelFlag(BusConstant.DEL_FLAG_N);
                lastGrowEnv.setCollectDate(lastGrowEnv.getCreateDate());
                lastGrowEnv.setExist(false);
            }
            return lastGrowEnv;
        }
        return null;
    }
}
