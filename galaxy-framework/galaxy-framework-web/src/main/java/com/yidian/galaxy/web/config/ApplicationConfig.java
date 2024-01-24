package com.yidian.galaxy.web.config;

import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * 配置注解
 *
 * @author changshuai.yuan create on 2024/1/18 18:23
 */
@Configuration
@EnableWebMvc
@ConfigurationPropertiesScan(basePackages = "com.yidian.galaxy.web.config.properties")
public class ApplicationConfig {

}
