package com.ams.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * @ClassName: DateConfig
 * @Author: chenxi
 * @Description: 统一时间格式处理
 * @Date: 2022/5/6 15:18
 */
@Configuration
public class DateConfig {

    private final String LocalDateTimeFormat="yyyy-MM-dd HH:mm:ss";
    private final String LocalDateFormat="yyyy-MM-dd";
    private final String LocalTimeFormat="HH:mm:ss";

    /**
     * @Description: TODO(设置时间类型接口统一返回格式)
     * @Return
     * @Date 2022/5/5 14:56
     */
    @Bean(name = "mapperObject")
    public ObjectMapper getObjectMapper() {
        ObjectMapper om = new ObjectMapper();
        JavaTimeModule javaTimeModule = new JavaTimeModule();
        javaTimeModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(LocalDateTimeFormat)));
        javaTimeModule.addSerializer(LocalDate.class, new LocalDateSerializer(DateTimeFormatter.ofPattern(LocalDateFormat)));
        javaTimeModule.addSerializer(LocalTime.class, new LocalTimeSerializer(DateTimeFormatter.ofPattern(LocalTimeFormat)));
        om.registerModule(javaTimeModule);
        return om;
    }

    /**
     * @param
     * @Description: TODO(日期转换器, java.lang.String - > java.util.Date)
     * @Return org.springframework.core.convert.converter.Converter<java.lang.String, java.util.Date>
     * @Date 2022/5/5 15:19
     */
    @Bean
    public Converter<String, Date> dateConverter() {
        return new Converter<String, Date>() {
            @Override
            public Date convert(String source) {
                SimpleDateFormat df = new SimpleDateFormat(LocalDateTimeFormat);
                Date date = null;
                try {
                    date = df.parse(source);
                } catch (Exception e) {
                    return null;
                }
                return date;
            }
        };
    }


    /**
     * @param
     * @Description: TODO(日期转换器, java.lang.String - > java.time.LocalDateTime)
     * @Return org.springframework.core.convert.converter.Converter<java.lang.String, java.time.LocalDateTime>
     * @Date 2022/5/5 15:20
     */
    @Bean
    public Converter<String, LocalDateTime> localDateTimeConverter() {
        return new Converter<String, LocalDateTime>() {

            @Override
            public LocalDateTime convert(String source) {
                DateTimeFormatter df = DateTimeFormatter.ofPattern(LocalDateTimeFormat);
                LocalDateTime date = null;
                try {
                    date = LocalDateTime.parse(source, df);
                } catch (Exception e) {
                    return null;
                }
                return date;
            }
        };
    }

    /**
     * @param
     * @Description: TODO(日期转换器, java.lang.String - > java.time.LocalDate)
     * @Return org.springframework.core.convert.converter.Converter<java.lang.String, java.time.LocalDate>
     * @Date 2022/5/5 15:20
     */
    @Bean
    public Converter<String, LocalDate> LocalDateConverter() {
        return new Converter<String, LocalDate>() {
            @Override
            public LocalDate convert(String source) {
                DateTimeFormatter df = DateTimeFormatter.ofPattern(LocalDateFormat);
                LocalDate date = null;
                try {
                    date = LocalDate.parse(source, df);
                } catch (Exception e) {
                    return null;
                }
                return date;
            }
        };
    }

    /**
     * @param
     * @Description: TODO(日期转换器, java.lang.String - > java.time.LocalTime)
     * @Return org.springframework.core.convert.converter.Converter<java.lang.String, java.time.LocalTime>
     * @Date 2022/5/5 15:20
     */
    @Bean
    public Converter<String, LocalTime> LocalTimeConvert() {
        return new Converter<String, LocalTime>() {
            @Override
            public LocalTime convert(String source) {
                DateTimeFormatter df = DateTimeFormatter.ofPattern(LocalTimeFormat);
                LocalTime date = null;
                try {
                    date = LocalTime.parse(source, df);
                } catch (Exception e) {
                    return null;
                }
                return date;
            }
        };
    }
}