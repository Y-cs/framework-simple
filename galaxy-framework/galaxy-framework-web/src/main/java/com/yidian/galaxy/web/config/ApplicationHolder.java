package com.yidian.galaxy.web.config;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;

import java.util.Optional;

/**
 * 应用容器
 *
 * @author changshuai.yuan create on 2024/1/20 10:20
 */
@Configuration
public class ApplicationHolder implements ApplicationContextAware {
    
    /**
     * 容器
     */
    private static Optional<ApplicationContext> context = Optional.empty();
    
    /**
     * 使用springAware的支持获取到容器
     *
     * @param applicationContext applicationContext
     * @throws BeansException BeansException
     */
    @Override
    public void setApplicationContext(@NonNull ApplicationContext applicationContext) throws BeansException {
        ApplicationHolder.context = Optional.of(applicationContext);
    }
    
    public static ApplicationContext getContext() {
        return ApplicationHolder.context.orElseThrow(() -> new RuntimeException("context is not ready"));
    }
    
}
