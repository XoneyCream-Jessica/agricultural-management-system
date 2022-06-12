package com.ams.filter;

import cn.hutool.core.util.StrUtil;
import com.ams.abnormal.ValidateCodeException;
import com.ams.config.security.handler.LocalAuthenticationFailureHandler;
import com.ams.constant.BusConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ProjectName: AMS
 * @ClassName: VerifyCodeFilter
 * @Author: chenxi
 * @Description: 验证码filter
 * @Date: 2022/5/6 23:21
 */

@Component
public class VerifyCodeFilter extends OncePerRequestFilter {
    @Autowired
    private LocalAuthenticationFailureHandler localAuthenticationFailureHandler;

    @Bean
    public VerifyCodeFilter getVerifyCodeFilter() {
        return new VerifyCodeFilter();
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        if (StrUtil.equals("/login", request.getRequestURI()) && StrUtil.equalsIgnoreCase(request.getMethod(), "post")) {
            String requestCaptcha = request.getParameter("code");
            try{
                if (requestCaptcha == null) {
                    throw new ValidateCodeException(BusConstant.VERIFICATION_CODE_NOT_EXIST);
                }
                String code = (String) request.getSession().getAttribute(BusConstant.SESSION_CODE);
                if (StrUtil.isEmpty(code)) {
                    throw new ValidateCodeException(BusConstant.VERIFICATION_CODE_PAST);
                }
                code = code.equals("0.0") ? "0" : code;
                if (!StrUtil.equals(code, requestCaptcha)) {
                    throw new ValidateCodeException(BusConstant.VERIFICATION_CODE_NOT_CORRECT);
                }
                filterChain.doFilter(request, response);
            }catch (AuthenticationException e){
                localAuthenticationFailureHandler.onAuthenticationFailure(request,response,  e);
            }
        }else{
            filterChain.doFilter(request, response);
        }

    }
}