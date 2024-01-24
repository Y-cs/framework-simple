package com.yidian.galaxy.app.config;

import com.yidian.galaxy.app.interceptor.UserInterceptor;
import com.yidian.galaxy.web.config.AbstractWebMvcConfig;
import com.yidian.galaxy.web.config.properties.SwaggerProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;

/**
 * Web配置
 *
 * @author changshuai.yuan create on 2024/1/19 18:39
 */
@Configuration
@RequiredArgsConstructor
public class WebConfig extends AbstractWebMvcConfig {
    
    private final UserInterceptor userInterceptor;
    
    private final SwaggerProperties swaggerProperties;
    
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //拦截所有
        InterceptorRegistration interceptorRegistration = registry.addInterceptor(userInterceptor)
                .addPathPatterns("/**");
        if (swaggerProperties.isEnable()) {
            //排除swagger-api
            interceptorRegistration.excludePathPatterns("/v3/api-docs");
        }
    }
    
}
