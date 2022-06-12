package com.ams.constant;

/**
 * @ProjectName: AMS
 * @ClassName: BusConstant
 * @Author: chenxi
 * @Description: 业务常量
 * @Date: 2022/5/5 1:06
 */
public class BusConstant {


    /**
     * 当前登录用户session key
     */
    public static final String SESSION_USER="loginUser";

    /**
     * 验证码session key
     */
    public static final String SESSION_CODE="yanzhengma";

    /**
     * 删除标志
     */
    public static final Integer DEL_FLAG_Y = 1;

    /**
     * 未删除
     */
    public static final Integer DEL_FLAG_N = 0;


    /**
     * 验证码不存在
     */
    public static final String VERIFICATION_CODE_NOT_EXIST="验证码不存在!";

    /**
     * 验证码过期
     */
    public static final String VERIFICATION_CODE_PAST="验证码过期!";

    /**
     * 验证码过期
     */
    public static final String VERIFICATION_CODE_NOT_CORRECT="验证码不正确!";

    /**
     * 系统错误
     */
    public static final String SYSTEM_ERROR="系统错误，请联系管理员!";


}