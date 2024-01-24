package com.yidian.galaxy.redis.support;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.util.concurrent.TimeUnit;

/**
 * Redis 对象操作
 *
 * @author changshuai.yuan create on 2024/1/19 14:23
 */
public class ObjectRedisOperations {
    
    private final ValueOperations<String, Object> operations;
    
    ObjectRedisOperations(RedisTemplate<String, Object> redisTemplate) {
        this.operations = redisTemplate.opsForValue();
    }
    
    public void set(String key, Object obj) {
        operations.set(key, obj);
    }
    
    public void set(String key, Object value, long seconds) {
        operations.set(key, value, seconds, TimeUnit.SECONDS);
    }
    
    public void set(String key, Object value, long seconds, TimeUnit unit) {
        operations.set(key, value, seconds, unit);
    }
    
    @SuppressWarnings("unchecked")
    public <T> T get(String key) {
        return (T) operations.get(key);
    }
    
}
