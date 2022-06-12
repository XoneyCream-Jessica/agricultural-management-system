package com.ams.abnormal;

import org.springframework.security.core.AuthenticationException;

/**
 * @ProjectName: AMS
 * @ClassName: ValidateCodeException
 * @Author: chenxi
 * @Description: 验证码异常
 * @Date: 2022/5/6 23:42
 */
public class ValidateCodeException extends AuthenticationException {
    public ValidateCodeException(String msg) {
        super(msg);
    }

}