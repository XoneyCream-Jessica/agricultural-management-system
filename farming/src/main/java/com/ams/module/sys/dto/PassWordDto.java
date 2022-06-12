package com.ams.module.sys.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @ProjectName: AMS
 * @ClassName: PassWordDto
 * @Author: chenxi
 * @Description: 修改密码dto
 * @Date: 2022-05-07 21:50
 */
@Data
public class PassWordDto {
    /**
     * 旧的密码
     */
    @NotEmpty(message = "旧的密码不能为空")
    private String odlPass;

    /**
     * 新的密码
     */
    @NotEmpty(message = "新的密码不能为空")
    private String newPass;

    /**
     * 确认密码
     */
    @NotEmpty(message = "确认密码不能为空")
    private String confirmPass;
}