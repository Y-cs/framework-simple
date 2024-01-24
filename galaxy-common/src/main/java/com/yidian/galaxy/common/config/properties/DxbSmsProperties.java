package com.yidian.galaxy.common.config.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * DxbSmsProperties
 *
 * @author changshuai.yuan create on 2024/1/24 18:37
 */
@Getter
@Setter
@ConfigurationProperties("project.dxb")
public class DxbSmsProperties {
    
    private String username;
    
    private String password;
    
    private String url;
    
}
