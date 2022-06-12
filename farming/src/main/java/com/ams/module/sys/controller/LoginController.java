package com.ams.module.sys.controller;

import com.ams.constant.BusConstant;
import com.ams.util.JsonResult;
import com.wf.captcha.ArithmeticCaptcha;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ProjectName: AMS
 * @ClassName: LoginController
 * @Author: chenxi
 * @Description: 登录相关接口
 * @Date: 2022-05-07 23:08
 */
@RestController
@RequestMapping("/sys-login")
@Api(tags = "登录相关接口 ")
public class LoginController {


    /**
     * 创建验证码
     *
     * @throws Exception
     */
    @GetMapping(value = "/getCode")
    public JsonResult getCode(HttpServletResponse response,HttpServletRequest request) throws Exception {
        ArithmeticCaptcha captcha = new ArithmeticCaptcha(111, 36);
        // 几位数运算，默认是两位
        captcha.setLen(2);
        // 获取运算的结果
        String result = captcha.text();
        // 保存
        request.getSession().setAttribute(BusConstant.SESSION_CODE,result);

        return JsonResult.success(captcha.toBase64());

    }
}