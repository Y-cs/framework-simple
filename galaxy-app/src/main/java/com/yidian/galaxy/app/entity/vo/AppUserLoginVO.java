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
public class AppUserLoginVO {
    
    public enum LoginTypeEnum {
        PHONE_PASSWORD
    }
    
    @NotNull
    @Schema(description = "登录类型")
    private LoginTypeEnum type = LoginTypeEnum.PHONE_PASSWORD;
    
    @NotBlank
    @Schema(description = "手机号")
    private String phone;
    
    @NotBlank
    @Schema(description = "密码")
    private String password;
    
    
}
