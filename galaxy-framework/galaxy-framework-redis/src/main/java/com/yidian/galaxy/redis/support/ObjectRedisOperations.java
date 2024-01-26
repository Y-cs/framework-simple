package com.yidian.galaxy.redis.support;

import com.yidian.galaxy.redis.entity.RedisKeyWrapper;
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
    
    /**
     * 获取
     *
     * @param key key
     * @param <T> value class
     * @return value
     */
    @SuppressWarnings("unchecked")
    public <T> T get(String key) {
        return (T) operations.get(key);
    }
    
    /**
     * set
     *
     * @param key key
     * @param obj value
     */
    public void set(String key, Object obj) {
        operations.set(key, obj);
    }
    
    /**
     * set
     *
     * @param value           value
     * @param redisKeyWrapper redisKeyWrapper
     * @param args            args
     */
    public void set(Object value, RedisKeyWrapper redisKeyWrapper, Object... args) {
        operations.set(redisKeyWrapper.keyFormat(args), value, redisKeyWrapper.getTimeout(), TimeUnit.SECONDS);
    }
    
    /**
     * set
     *
     * @param key     key
     * @param value   value
     * @param seconds seconds
     */
    public void set(String key, Object value, long seconds) {
        operations.set(key, value, seconds, TimeUnit.SECONDS);
    }
    
    /**
     * set
     *
     * @param key     key
     * @param value   value
     * @param seconds seconds
     * @param unit    unit
     */
    public void set(String key, Object value, long seconds, TimeUnit unit) {
        operations.set(key, value, seconds, unit);
    }
    
}
