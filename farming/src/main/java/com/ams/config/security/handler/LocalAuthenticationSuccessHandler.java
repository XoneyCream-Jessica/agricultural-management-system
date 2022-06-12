package com.ams.config.security.handler;

import com.alibaba.fastjson.JSON;
import com.ams.util.JsonResult;
import com.ams.util.UserUtil;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ProjectName: AMS
 * @ClassName: LocalAuthenticationSuccessHandler
 * @Author: chenxi
 * @Description: 登录成功处理
 * @Date: 2022/5/5 15:35
 */
@Component
public class LocalAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException {
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().print(JSON.toJSONString(JsonResult.success(UserUtil.getUser())));
    }
}