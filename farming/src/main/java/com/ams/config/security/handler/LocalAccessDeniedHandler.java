package com.ams.config.security.handler;

import com.alibaba.fastjson.JSON;
import com.ams.util.JsonResult;
import com.ams.util.ResultEnum;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ProjectName: AMS
 * @ClassName: LocalAccessDeniedHandler
 * @Author: chenxi
 * @Description: 无权访问处理
 * @Date: 2022/5/5 16:45
 */

@Component
public class LocalAccessDeniedHandler  implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException e) throws IOException, ServletException {
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().print(JSON.toJSONString(JsonResult.error(ResultEnum.USER_NO_ACCESS.getMessage(),ResultEnum.USER_NO_ACCESS.getCode())));
    }
}