package com.yidian.galaxy.web.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.yidian.galaxy.cornerstone.json.JsonLocalDateTimeSupport;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.time.LocalDateTime;
import java.util.List;

/**
 * WebConfig
 *
 * @author changshuai.yuan create on 2024/1/18 18:23
 */
public abstract class AbstractWebMvcConfig implements WebMvcConfigurer {
    
    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        //统一返回值处理
        JavaTimeModule javaTimeModule = new JavaTimeModule();
        javaTimeModule.addSerializer(LocalDateTime.class, new JsonLocalDateTimeSupport.LocalDateTimeSerializer());
        javaTimeModule.addDeserializer(LocalDateTime.class, new JsonLocalDateTimeSupport.LocalDateTimeDeserializer());
        ObjectMapper objectMapper = Jackson2ObjectMapperBuilder.json().modules(javaTimeModule).build();
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter(objectMapper);
        converters.add(0, converter);
    }
    
}
