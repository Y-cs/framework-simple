package com.yidian.galaxy.app.entity.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * AppUserInfoDto
 *
 * @author changshuai.yuan create on 2024/1/22 17:50
 */
@Data
@Schema(name = "App用户信息")
public class AppUserInfoDto {
    
    @Schema(description = "账户Id")
    private Long accountId;
    
    @Schema(description = "用户Id")
    private Long userId;
    
    @Schema(description = "手机号")
    private String phone;
    
    @Schema(description = "昵称")
    private String nikeName;
    
    @Schema(description = "性别")
    private Integer sex;
    
    @Schema(description = "头像")
    private String headImg;
    
    @Schema(description = "邀请码")
    private String inviteCode;
    
    @Schema(description = "实名认证状态")
    private Integer authenticationStatus;
    
    @Schema(description = "token")
    private String token;
    
    @Schema(description = "创建时间")
    private LocalDateTime createTime;
    
    @Schema(description = "真实姓名")
    private String name;
    
    @Schema(description = "身份证号")
    private String idCard;
    
}
