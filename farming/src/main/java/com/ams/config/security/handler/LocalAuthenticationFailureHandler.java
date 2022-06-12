package com.ams.config.security.handler;

import com.alibaba.fastjson.JSON;
import com.ams.util.JsonResult;
import com.ams.util.ResultEnum;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ProjectName: AMS
 * @ClassName: LocalAuthenticationFailureHandler
 * @Author: chenxi
 * @Description: 登录失败的处理
 * @Date: 2022/5/6 15:42
 */

@Component
public class LocalAuthenticationFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException {
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json;charset=utf-8");
        if (e instanceof BadCredentialsException) {
            //帐号不存在或者密码错误
            response.getWriter().print(JSON.toJSONString(JsonResult.error(ResultEnum.USER_LOGIN_FAILED.getMessage(), ResultEnum.USER_LOGIN_FAILED.getCode())));
        }else{
            response.getWriter().print(JSON.toJSONString(JsonResult.error(e.getMessage())));
        }
    }
}