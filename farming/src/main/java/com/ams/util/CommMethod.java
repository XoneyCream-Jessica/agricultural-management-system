/**
 * 
 */
package com.ams.util;

import cn.hutool.core.util.StrUtil;
import com.ams.constant.BusConstant;
import lombok.experimental.UtilityClass;
import org.springframework.util.Assert;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 *
 * @ProjectName: AMS
 * @ClassName: CommMethod
 * @Author: chenxi
 * @Description: 公共静态方法类
 * @Date: 2022-05-08 15:30
 */
@UtilityClass
public class CommMethod {
	private final String UNKNOWN = "unknown";
	/**
	 * 
	 * @Title: parseDate
	 * @Description:TODO(转换日期类型)
	 * @author
	 * @param source 源字符串
	 * @return
	 *
	 */
	public Date parseDate(String source) {
		if (source == null || source.trim().equals("")) {
			return null;
		}
		String datestr = source.trim();
		// 处理日期格式
		String regex = "^(\\d+)[-/\\\\.年 ](\\d+)[-/\\\\.月 ](\\d+)日?";
		datestr = datestr.replaceAll(regex, "$1-$2-$3");
		datestr = datestr.replaceFirst("^(\\d+)-(\\d+)-(\\d+) +(\\d+):(\\d+):(\\d+)$", "$1-$2-$3 $4:$5:$6.000")
			.replaceFirst("^(\\d+)-(\\d+)-(\\d+)$", "$1-$2-$3 00:00:00.000");
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		try {
			Date date = dateFormat.parse(datestr);
			return date;
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 
	 * @Title: parseLocalDateTime
	 * @Description:TODO(转换java8日期类型)
	 * @author
	 * @param source 源字符串
	 * @return
	 *
	 */
	public LocalDateTime parseLocalDateTime(String source) {
		return dateToLocalDateTime(parseDate(source));
	}


	/**
	 *
	 * @Title: parseLocalDate
	 * @Description:TODO(转换java8日期类型)
	 * @author
	 * @param source 源字符串
	 * @return
	 *
	 */
	public static LocalDate parseLocalDate(String source) {
		return dateToLocalDate(parseDate(source));
	}


	/**
	 * 
	 * @Title: localDateTimeToDate
	 * @Description:TODO(java8LocalDateTime转java.util.Date)
	 * @author
	 * @param localDateTime
	 * @return
	 *
	 */
	public Date localDateTimeToDate(LocalDateTime localDateTime) {
		if (localDateTime == null) {
			return null;
		}
		Instant instant = localDateTime.atZone(ZoneId.systemDefault()).toInstant();
		return Date.from(instant);
	}

	/**
	 * 
	 * @Title: dateToLocalDateTime
	 * @Description:TODO(java.util.Date转java8LocalDateTime)
	 * @author
	 * @param date
	 * @return
	 *
	 */
	public LocalDateTime dateToLocalDateTime(Date date) {
		if (date == null) {
			return null;
		}
		return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
	}

	/**
	 *
	 * @Title: dateToLocalDate
	 * @Description:TODO(java.util.Date转java8LocalDate)
	 * @author
	 * @param date
	 * @return
	 *
	 */
	public LocalDate dateToLocalDate(Date date) {
		if (date == null) {
			return null;
		}
		return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	}



	public String getIP(HttpServletRequest request) {
		Assert.notNull(request, "HttpServletRequest is null");
		String ip = request.getHeader("X-Requested-For");
		if (StrUtil.isBlank(ip) || UNKNOWN.equalsIgnoreCase(ip)) {
			ip = request.getHeader("X-Forwarded-For");
		}
		if (StrUtil.isBlank(ip) || UNKNOWN.equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (StrUtil.isBlank(ip) || UNKNOWN.equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (StrUtil.isBlank(ip) || UNKNOWN.equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_CLIENT_IP");
		}
		if (StrUtil.isBlank(ip) || UNKNOWN.equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if (StrUtil.isBlank(ip) || UNKNOWN.equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return StrUtil.isBlank(ip) ? null : ip.split(",")[0];
	}


	/**
	 * 创建对象初始化
	 * @param obj
	 */
	public static void beanCreate(Object obj) {
		//设置创建时间
		setFieldValueByName(obj, "createDate", DateUtil.nowLocalDateTime());
		//设置创建人
		setFieldValueByName(obj, "createUser", UserUtil.getUserId());
		//设置软删除
		setFieldValueByName(obj, "delFlag", BusConstant.DEL_FLAG_N);
	}

	/**
	 * 修改对象初始化
	 * @param obj
	 */
	public static void beanUpdate(Object obj) {
		//设置创建时间
		setFieldValueByName(obj, "updateDate", DateUtil.nowLocalDateTime());
		//设置创建人
		setFieldValueByName(obj, "updateUser", UserUtil.getUserId());
	}


	/**
	 * 通过反射写一个类，类中有个方法。
	 * 该方法可以设置某个类中的某个属性（构造方法，成员变量，成员方法）为特定的值
	 * @param obj
	 * @param fieldName
	 * @param value
	 */
	public static void setFieldValueByName(Object obj, String fieldName, Object value){
		try {
			// 获取obj类的字节文件对象
			Class c = obj.getClass();
			// 获取该类的成员变量
			Field f = c.getDeclaredField(fieldName);
			if(f!=null){
				// 取消语言访问检查
				f.setAccessible(true);
				// 给变量赋值
				f.set(obj, value);
			}
		} catch (Exception e) {
		}
	}

	/**
	 * 根据属性名获取属性值
	 * */
	private Object getFieldValueByName(String fieldName, Object o) {
		try {
			String firstLetter = fieldName.substring(0, 1).toUpperCase();
			String getter = "get" + firstLetter + fieldName.substring(1);
			Method method = o.getClass().getMethod(getter, new Class[] {});
			Object value = method.invoke(o, new Object[] {});
			return value;
		} catch (Exception e) {

			return null;
		}
	}

}
