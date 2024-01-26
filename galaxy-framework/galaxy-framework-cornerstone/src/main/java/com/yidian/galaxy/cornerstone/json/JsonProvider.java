package com.yidian.galaxy.cornerstone.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.time.LocalDateTime;

/**
 * Json支持
 *
 * @author changshuai.yuan create on 2024/1/18 16:34
 */
public class JsonProvider {
    
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    
    static {
        JavaTimeModule javaTimeModule = new JavaTimeModule();
        javaTimeModule.addSerializer(LocalDateTime.class, new JsonLocalDateTimeSupport.LocalDateTimeSerializer());
        javaTimeModule.addDeserializer(LocalDateTime.class, new JsonLocalDateTimeSupport.LocalDateTimeDeserializer());
        OBJECT_MAPPER.registerModule(javaTimeModule);
    }
    
    public static ObjectMapper getJson() {
        return OBJECT_MAPPER;
    }
    
}
