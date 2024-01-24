package com.yidian.galaxy.app;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * Galaxy-App后端项目
 *
 * @author changshuai.yuan DateTime: 2024/1/10 12:01
 */
@Slf4j
@SpringBootApplication(scanBasePackages = "com.yidian.galaxy")
public class GalaxyAppApplication {
    
    public static void main(String[] args) {
        
        SpringApplication application = new SpringApplication(GalaxyAppApplication.class);
        ConfigurableApplicationContext applicationContext = application.run(args);
        log.info("{}容器已启动", applicationContext.getId());
        
    }
    
}
