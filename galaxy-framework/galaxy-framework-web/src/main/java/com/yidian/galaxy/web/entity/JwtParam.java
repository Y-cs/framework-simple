package com.yidian.galaxy.web.entity;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * JWT对象
 *
 * @author changshuai.yuan DateTime: 2024/1/2 9:37
 */
@Data
@Accessors(chain = true)
public class JwtParam {
    
    private Long accountId;
    
    private Long userId;
    
    private Integer system;
}
