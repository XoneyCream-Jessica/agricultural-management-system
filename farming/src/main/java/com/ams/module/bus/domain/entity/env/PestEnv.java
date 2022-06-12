package com.ams.module.bus.domain.entity.env;

import com.ams.module.bus.broker.EventBroker;
import com.ams.module.bus.domain.vo.InhibitionEvent;
import com.ams.module.bus.domain.vo.PestInhibitionInfo;
import com.ams.module.bus.domain.vo.PestRecognizeEvent;
import com.ams.module.dl.DLModelManager;
import com.ams.util.Base64Util;
import com.ams.util.DateUtil;
import com.ams.util.PestUtil;
import com.google.common.eventbus.Subscribe;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

public class PestEnv extends Environment {
    private Random random = new Random();

    public long generateInterval = DateUtil.calculateMs(10 * 60 * 1000);

    private static File[] imageFolder;

    static {
        try {
            imageFolder = ResourceUtils.getFile("classpath:test").listFiles();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public PestEnv() {

    }

    private Map<String, Long> pesticideDuration = new ConcurrentHashMap<>();

    @Override
    protected long getConfigGenerateInterval() {
        return generateInterval;
    }

    public PestEnv(Long id, String name) {
        super(id, name);
    }

    @Override
    public void generateData() {
        int randomInt = Math.abs(random.nextInt());

        int probability = 5;
        if (pesticideDuration.size() > 0) {
            probability = 10;
        }
        // 1/5的概率预测一次
        if (randomInt % probability == 1) {
            File imgFolder = imageFolder[Math.abs(randomInt % imageFolder.length)];
            File[] imgs = imgFolder.listFiles();
            assert imgs != null;
            File img = imgs[Math.abs(randomInt % imgs.length)];
            String predictLabel = DLModelManager.predictFromResources(img.getName(), imgFolder.getName());
            if ((predictLabel != null) && !pesticideDuration.containsKey(PestUtil.getPesticideByPest(predictLabel))) {
                EventBroker.post(EventBroker.EventType.getEnvTopic(getId()), new PestRecognizeEvent(getId(), Base64Util.fileConvertBase64(img), predictLabel, imgFolder.getName()));
            }
        }
        Iterator<Map.Entry<String, Long>> iter = pesticideDuration.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry<String, Long> entry = iter.next();
            long duration = entry.getValue() - getGenerateInterval();
            if (duration <= 0) {
                iter.remove();
            } else {
                pesticideDuration.put(entry.getKey(), duration);
            }
        }
    }

    @Subscribe
    public void onInhibitionEvent(InhibitionEvent event) {
        Object info = event.getInhibitionInfo();
        if (info instanceof PestInhibitionInfo) {
            PestInhibitionInfo pestInhibitionInfo = (PestInhibitionInfo) info;
            pesticideDuration.put(pestInhibitionInfo.getPesticide(), DateUtil.calculateMs(360 * 60 * 1000));
        }
    }

    public Map<String, Long> getPesticideDuration() {
        return pesticideDuration;
    }

    public void setPesticideDuration(Map<String, Long> pesticideDuration) {
        this.pesticideDuration = pesticideDuration;
    }
}
