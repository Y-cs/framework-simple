package com.yidian.galaxy.app.entity.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * AppUserRegisterVo
 *
 * @author changshuai.yuan create on 2024/1/24 14:20
 */
@Data
@Schema(description = "App用户注册对象")
public class AppUserRegisterVo {
    
    @Schema(description = "手机号")
    @NotBlank(message = "手机号不能为空")
    private String phone;
    
    @Schema(description = "验证码")
    @NotBlank(message = "验证码不能为空")
    private String verificationCode;
    
    @Schema(description = "密码")
    @NotBlank(message = "密码不能为空")
    private String password;
    
    @Schema(description = "邀请码")
    private String inviteCode;
    
}
