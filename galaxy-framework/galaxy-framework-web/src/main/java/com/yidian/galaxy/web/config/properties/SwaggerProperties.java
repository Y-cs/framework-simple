package com.yidian.galaxy.web.config.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Swagger 配置
 *
 * @author changshuai.yuan create on 2024/1/18 18:20
 */
@Getter
@Setter
@ConfigurationProperties("swagger")
public class SwaggerProperties {
    
    private boolean enable = false;
    
    private String basePackage;
    
}
