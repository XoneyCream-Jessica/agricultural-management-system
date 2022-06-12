package com.ams.module.bus.domain.enums;

/**
 * 处理器类型
 */
public enum HandlerType {
    NOTIFY("NotifyHandler"),
    PESTKILLER("PestKillerHandler"),
    HUMITURE("HumitureHandler"),
    TEMPERATURE("TemperatureHandler"),
    PERSIST("PersistHandler"),

    DEFAULT("DEFAULT")

    ;


    private String name;

    public String getName() {
        return name;
    }

    HandlerType(String name) {
        this.name = name;
    }

    public static HandlerType findHandlerTypeByName(String name) {
        for (HandlerType handlerType : values()) {
            if (handlerType.name.equals(name)) {
                return handlerType;
            }
        }
        return null;
    }
}
