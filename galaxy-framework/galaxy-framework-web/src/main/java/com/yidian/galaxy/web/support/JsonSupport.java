package com.yidian.galaxy.web.support;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.yidian.galaxy.cornerstone.json.JsonProvider;
import com.yidian.galaxy.web.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

import static com.yidian.galaxy.web.exception.SystemExceptionEnum.DATA_EXCEPTION;

/**
 * JsonSupport
 *
 * @author changshuai.yuan create on 2024/1/26 10:27
 */
@Slf4j
public class JsonSupport {
    
    private static final ObjectMapper OBJECTMAPPER = JsonProvider.getJson();
    
    private JsonSupport() {
    }
    
    public static <T> T parse(String json, Class<T> clz) {
        return tryDo(() -> OBJECTMAPPER.readValue(json, clz));
    }
    
    public static <T> T parseType(String json, JavaType javaType) {
        return tryDo(() -> OBJECTMAPPER.readValue(json, javaType));
    }
    
    public static <K, V> Map<K, V> parseMap(String json, Class<K> kClass, Class<V> vClass) {
        return parseType(json, OBJECTMAPPER.getTypeFactory().constructMapType(Map.class, kClass, vClass));
    }
    
    public static <T> List<T> parseList(String json, Class<T> clz) {
        return parseType(json, OBJECTMAPPER.getTypeFactory().constructCollectionType(List.class, clz));
    }
    
    public static String toJson(Object obj) {
        return tryDo(() -> OBJECTMAPPER.writeValueAsString(obj));
    }
    
    public static TypeFactory getTypeFactory() {
        return OBJECTMAPPER.getTypeFactory();
    }
    
    /**
     * 执行操作
     *
     * @param callable 操作
     * @param <T>      返回泛型
     * @return 返回
     */
    public static <T> T tryDo(Callable<T> callable) {
        try {
            return callable.call();
        } catch (Exception e) {
            log.error("Json转换错误", e);
            throw new BusinessException(DATA_EXCEPTION);
        }
    }
    
}
