package com.yidian.galaxy.common.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Configuration;

/**
 * 加载一些配置
 *
 * @author changshuai.yuan create on 2024/1/24 18:40
 */
@Configuration
@ConfigurationPropertiesScan(basePackages = "com.yidian.galaxy.common.config.properties")
@MapperScan("com.yidian.galaxy.common.mapper")
public class CommonConfig {

}
