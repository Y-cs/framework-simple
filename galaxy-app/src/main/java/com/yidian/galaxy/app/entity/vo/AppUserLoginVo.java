package com.yidian.galaxy.app.entity.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * UserLoginPasswordVO
 *
 * @author changshuai.yuan create on 2024/1/22 17:47
 */
@Data
@Schema(name = "App用户登录对象")
public class AppUserLoginVo {
    
    public interface PhonePasswordLogin {
    
    }
    
    public enum LoginTypeEnum {
        PHONE_PASSWORD
    }
    
    @NotNull(message = "登录方式不能为空")
    @Schema(description = "登录类型")
    private LoginTypeEnum type = LoginTypeEnum.PHONE_PASSWORD;
    
    @NotBlank(message = "手机号不能为空", groups = PhonePasswordLogin.class)
    @Schema(description = "手机号")
    private String phone;
    
    @NotBlank(message = "密码不能为空", groups = PhonePasswordLogin.class)
    @Schema(description = "密码")
    private String password;
    
    
}
