package com.ams.util;

import org.springframework.util.ResourceUtils;

import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

/**
 * @author:
 * @date: 2022-05-08 14:24
 * @description: 时间工具类
 */
public class DateUtil {

    public final static SimpleDateFormat sdfTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private static SimpleDateFormat sdf_date_format = new SimpleDateFormat("yyyy-MM-dd");

    public static final ThreadLocal<SimpleDateFormat> timeFormat = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyyMMdd-HH:mm:ss");
        }
    };

    private static SpeedUpTime speedUpTime;

    static {
        fillAllConfig();
    }


    private static void fillAllConfig() {
        Map<String, String> config = resolveSpeedUpConfig();
        speedUpTime = new SpeedUpTime(config);
    }

    /**
     * 计算加速后的毫秒数
     */
    public static long calculateMs(long millisecond) {
        if (speedUpTime != null) {
            return speedUpTime.calculateMs(millisecond);
        } else {
            return millisecond;
        }
    }

    private static Map<String, String> resolveSpeedUpConfig() {
        Map<String, String> config = new HashMap<>();
        try {
            File speedUpConfig = ResourceUtils.getFile("classpath:speedup.config");
            try (FileInputStream fis = new FileInputStream(speedUpConfig);
                 InputStreamReader isr = new InputStreamReader(fis);
                 BufferedReader reader = new BufferedReader(isr)) {
                String lineTxt;
                while ((lineTxt = reader.readLine()) != null) {
                    String[] arr = lineTxt.split("=");
                    if (arr.length == 2) {
                        config.put(arr[0].trim(), arr[1].trim());
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return config;
    }


    public static Date nowDate() {
        if (speedUpTime != null) {
            return speedUpTime.nowDate();
        } else {
            return new Date();
        }
    }

    public static LocalDateTime nowLocalDateTime() {
        return LocalDateTime.ofInstant(nowDate().toInstant(), ZoneId.systemDefault());
    }

    /**
     * 获取当前时间的YYYY-MM-DD HH:mm:ss格式
     *
     * @return
     */
    public static String getTime() {
        return sdfTime.format(nowDate());
    }

    public static long getTimestamp() {
        return nowDate().getTime();
    }

    /**
     * 日期比较，如果s>=e 返回true 否则返回false
     *
     * @param s
     * @param e
     * @return
     */
    public static boolean compareDate(String s, String e) {
        if (fomatDate(s) == null || fomatDate(e) == null) {
            return false;
        }
//        return fomatDate(s).getTime() >=fomatDate(e).getTime();
        return s.compareTo(e) > 0;
    }

    /**
     * 格式化日期
     *
     * @param date
     * @return
     */
    public static Date fomatDate(String date) {
        DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return fmt.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获取当前时间的后i天
     *
     * @param i
     * @return
     */
    public static String getAddDay(int i) {
        String currentTime = DateUtil.getTime();
        GregorianCalendar gCal = new GregorianCalendar(
                Integer.parseInt(currentTime.substring(0, 4)),
                Integer.parseInt(currentTime.substring(5, 7)) - 1,
                Integer.parseInt(currentTime.substring(8, 10)));
        gCal.add(GregorianCalendar.DATE, i);
        return sdf_date_format.format(gCal.getTime());
    }

    /**
     * 获取当前时间的后i天
     * 精确到秒
     *
     * @param i
     * @return
     */
    public static String getAddDayTime(int i) {
        Date date = new Date(System.currentTimeMillis() + i * 24 * 60 * 60 * 1000);
        return sdfTime.format(date);
    }

    /**
     * 获取当前时间的+多少秒
     * 精确到秒
     *
     * @param i
     * @return
     */
    public static String getAddDaySecond(int i) {
        Date date = new Date(System.currentTimeMillis() + i * 1000);
        return sdfTime.format(date);
    }

    public static long localDateTimeToTimestamp(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant()).getTime();
    }
}
