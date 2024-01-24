package com.yidian.galaxy.web.config;

import com.yidian.galaxy.web.config.properties.SwaggerProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Optional;

/**
 * Swagger 配置
 *
 * @author changshuai.yuan create on 2024/1/18 18:17
 */
@Configuration
@EnableOpenApi
@ConditionalOnBean(SwaggerProperties.class)
@ConditionalOnWebApplication
@ConditionalOnProperty(name = "swagger-config.enable", havingValue = "true")
@RequiredArgsConstructor
public class SwaggerConfig {
    
    private final SwaggerProperties swaggerProperties;
    
    @Bean
    public Docket createRestApi() {
        if (swaggerProperties.isEnable()) {
            String basePackage = Optional.ofNullable(swaggerProperties.getBasePackage())
                    .orElseThrow(() -> new Error("miss swagger base package"));
            return new Docket(DocumentationType.OAS_30).useDefaultResponseMessages(true).forCodeGeneration(false)
                    .select().apis(RequestHandlerSelectors.basePackage(basePackage))
                    .paths(PathSelectors.any()).build();
        }
        return null;
    }
    
}
