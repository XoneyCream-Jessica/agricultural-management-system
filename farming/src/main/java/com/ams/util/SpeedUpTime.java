package com.ams.util;

import org.springframework.util.ResourceUtils;

import java.io.*;
import java.text.ParseException;
import java.util.Date;
import java.util.Map;

public class SpeedUpTime {
    /**
     * 是否开启加速
     */
    private boolean enableSpeedUp;

    /**
     * 加速倍数
     */
    private int speedUpStep;

    /**
     * 实际开始的时间，System.currentTime()
     */
    private long beginRealTime;

    /**
     * 开始加速的时间，如果加速时间持久化了，则取文件中的，反则取beginTime
     */
    private long startSpeedTime;

    /**
     * 是否持久化加速时间，如果开启，每次获取nowDate的时候，会将加速时间存入文件中，用于下次系统启动时的时间恢复.
     */
    private boolean speedUpTimePersist;

    /**
     * 最后一次加速时间
     */
    private long lastSpeedUpTime;

    /**
     * 如果是debug模式,则每次speeduptime更新时候会持久化,run的时候需要将该参数关闭,避免影响性能
     */
    private boolean isDebug;

    SpeedUpTime(Map<String, String> config) {
        enableSpeedUp = Boolean.parseBoolean(config.get("speedup.enable"));
        speedUpStep = Integer.valueOf(config.get("speedup.step"));
        String beginTime = config.get("speedup.begintime");
        speedUpTimePersist = Boolean.parseBoolean(config.get("speedup.persist"));
        isDebug = Boolean.parseBoolean(config.get("speedup.debug"));
        if (enableSpeedUp) {
            String lastSpeedUpTimeFromRedis = getLastSpeedUpTimeFromFile();
            if (lastSpeedUpTimeFromRedis != null && speedUpTimePersist) {
                startSpeedTime = Long.valueOf(lastSpeedUpTimeFromRedis);
            } else {
                try {
                    startSpeedTime = DateUtil.timeFormat.get().parse(beginTime).getTime();
                } catch (ParseException e) {
                    enableSpeedUp = false;
                }
            }
            updateLastSpeedUpTime(startSpeedTime);
            beginRealTime = System.currentTimeMillis();
        }
        Runtime.getRuntime().addShutdownHook(new Thread(this::saveCheckPoint));
    }

    private static String getLastSpeedUpTimeFromFile() {
        try {
            File lastSpeedTime = getLastSpeedTimeFile();
            try (FileInputStream fis = new FileInputStream(lastSpeedTime);
                 InputStreamReader isr = new InputStreamReader(fis);
                 BufferedReader reader = new BufferedReader(isr)) {
                String lineTxt;
                while ((lineTxt = reader.readLine()) != null) {
                    return lineTxt;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static File getLastSpeedTimeFile() throws FileNotFoundException {
        File config = ResourceUtils.getFile("classpath:speedup.config");
        File lastSpeedTime = new File(config.getParentFile().getParentFile().getParentFile().getAbsolutePath() + File.separator + "lastspeeduptime");
        if (!lastSpeedTime.exists()) {
            lastSpeedTime.mkdir();
        }
        return lastSpeedTime;
    }

    long calculateMs(long millisecond) {
        if (enableSpeedUp) {
            return (long) ((double) millisecond / speedUpStep);
        } else {
            return millisecond;
        }
    }

    private void updateLastSpeedUpTime(Long lastSpeedUpTime) {
        this.lastSpeedUpTime = lastSpeedUpTime;
        if (isDebug) {
            saveCheckPoint();
        }
    }

    Date nowDate() {
        if (enableSpeedUp) {
            long deltaTime = (System.currentTimeMillis() - beginRealTime);
            Date speedUpDate = new Date(deltaTime * speedUpStep + startSpeedTime);
            updateLastSpeedUpTime(speedUpDate.getTime());
            return speedUpDate;
        } else {
            return new Date();
        }
    }

    private void saveCheckPoint() {
        if (!speedUpTimePersist) {
            return;
        }
        try {
            File lastSpeedTime = getLastSpeedTimeFile();
            try (FileWriter fileWriter = new FileWriter(lastSpeedTime.getPath());
                 BufferedWriter bufferWriter = new BufferedWriter(fileWriter)) {
                bufferWriter.write(String.valueOf(lastSpeedUpTime));
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 进程被杀掉的时候,保存checkpoint
     */
    @Override
    protected void finalize() {
        saveCheckPoint();
    }

}
