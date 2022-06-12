package com.ams.module.sys.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @ProjectName: AMS
 * @ClassName: RegisteredDto
 * @Author: chenxi
 * @Description: 注册dto
 * @Date: 2022-05-07 13:07
 */
@Data
public class RegisteredDto {
    /**
     * 用户名
     */
    @NotEmpty(message = "用户名不能为空")
    private String username;
    /**
     * 密码
     */
    @NotEmpty(message = "密码不能为空")
    private String password;

    /**
     * 确认密码
     */
    private String confirmPassword;
}