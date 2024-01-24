package com.yidian.galaxy.redis.support;

import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Map;

/**
 * Redis hash的支持
 *
 * @author changshuai.yuan create on 2024/1/19 14:43
 */
public class HashRedisOperations {
    
    private final HashOperations<String, Object, Object> operations;
    
    public HashRedisOperations(RedisTemplate<String, Object> redisTemplate) {
        this.operations = redisTemplate.opsForHash();
    }
    
    public void hSet(String key, Object field, Object value) {
        this.operations.put(key, field, value);
    }
    
    public void hmSet(String key, Map<Object, Object> fieldValues) {
        this.operations.putAll(key, fieldValues);
    }
    
    @SuppressWarnings("unchecked")
    public <V> V hGet(String key, Object field) {
        return (V) this.operations.get(key, field);
    }
    
    @SuppressWarnings("unchecked")
    public <K, V> Map<K, V> hGetAll(String key) {
        return (Map<K, V>) this.operations.entries(key);
    }
    
    public void hDelete(String key, Object... fields) {
        this.operations.delete(key, fields);
    }
    
}
