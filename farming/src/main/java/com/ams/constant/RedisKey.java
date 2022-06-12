package com.ams.constant;

public class RedisKey {

    private static final String limiter = ":";

    private static final RedisKeyInfo SENSOR_BIND_INFO = new RedisKeyInfo("SENSOR").append("BIND").append("INFO");

    private static final RedisKeyInfo LISTENER_INFO = new RedisKeyInfo("LISTENER").append("INFO");

    private static final RedisKeyInfo LISTENER_BAND_INFO = new RedisKeyInfo("LISTENER").append("BAND").append("INFO");

    private static final RedisKeyInfo EVN_INFO = new RedisKeyInfo("ENV").append("INFO");

    private static final RedisKeyInfo SENSOR_INFO = new RedisKeyInfo("SENSOR").append("INFO");


    public static String getSensorBindInfoKey(Object sensorId) {
        return SENSOR_BIND_INFO.append(sensorId).toString();
    }

    public static String getListenerInfoKey(Object listenerId) {
        return LISTENER_INFO.append(listenerId).toString();
    }

    public static String getListenerBandInfoKey(Object listenerId, Object sensorId) {
        return LISTENER_BAND_INFO.append(listenerId).append(sensorId).toString();
    }

    public static String getEnvInfoKey(Object envId) {
        return EVN_INFO.append(envId).toString();
    }

    public static String getSensorInfoKey(Object sensorId) {
        return SENSOR_INFO.append(sensorId).toString();
    }

    public static String getLastKeyField(String key) {
        String[] arr = key.split(limiter);
        return arr[arr.length - 1];
    }

    private static class RedisKeyInfo {
        private StringBuilder key;

        public RedisKeyInfo(String key) {
            this.key = new StringBuilder(key);
        }

        private RedisKeyInfo(StringBuilder key){
            this.key = key;
        }

        public RedisKeyInfo append(Object field) {
            return new RedisKeyInfo(new StringBuilder(key).append(limiter).append(String.valueOf(field)));
        }

        @Override
        public String toString() {
            return key.toString();
        }
    }
}
