package com.yidian.galaxy.app.entity.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * AppUserUpdateVo
 *
 * @author changshuai.yuan create on 2024/1/25 18:15
 */
@Data
@Schema(description = "用户信息更新")
public class AppUserUpdateVo {
    
    @Schema(description = "id")
    private Long id;
    
    @Schema(description = "头像")
    private String headImg;
    
    @Schema(description = "昵称")
    private String nikeName;
    
    @Schema(description = "性别")
    private Integer sex;
    
    @Schema(description = "密码")
    private String password;
    
}
