package com.ams.config.security.handler;

import com.ams.util.UserUtil;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ProjectName: AMS
 * @ClassName: LocalLogoutSuccessHandler
 * @Author: chenxi
 * @Description: 退出登录
 * @Date: 2022/5/5 20:27
 */

@Component
public class LocalLogoutSuccessHandler  implements LogoutSuccessHandler {
    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json;charset=utf-8");
        //清空用户信息
        UserUtil.clear();
//        response.getWriter().print(JSON.toJSONString(JsonResult.success(ResultEnum.USER_LOGOUT_SUCCESS.getMessage())));
    }

}