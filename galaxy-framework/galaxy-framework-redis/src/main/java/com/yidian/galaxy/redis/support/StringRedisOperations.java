package com.yidian.galaxy.redis.support;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

/**
 * Redis String支持
 *
 * @author changshuai.yuan create on 2024/1/19 11:44
 */
public class StringRedisOperations {
    
    private final ValueOperations<String, String> operations;
    
    StringRedisOperations(RedisTemplate<String, String> redisTemplate) {
        this.operations = redisTemplate.opsForValue();
    }
    
    public String get(String key) {
        return operations.get(key);
    }
    
    public void set(String key, String value) {
        operations.set(key, value);
    }
    
    public Long incr(String key) {
        return operations.increment(key);
    }
    
}
