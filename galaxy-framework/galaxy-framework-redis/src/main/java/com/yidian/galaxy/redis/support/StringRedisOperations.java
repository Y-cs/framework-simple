package com.yidian.galaxy.redis.support;

import com.yidian.galaxy.redis.entity.RedisKeyWrapper;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.util.concurrent.TimeUnit;

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
    
    /**
     * 获取
     *
     * @param key key
     * @return value
     */
    public String get(String key) {
        return operations.get(key);
    }
    
    /**
     * 设置
     *
     * @param key   key
     * @param value value
     */
    public void set(String key, String value) {
        operations.set(key, value);
    }
    
    /**
     * 设置
     *
     * @param value           value
     * @param redisKeyWrapper redisKeyWrapper
     * @param args            key args
     */
    public void set(String value, RedisKeyWrapper redisKeyWrapper, Object... args) {
        operations.set(redisKeyWrapper.keyFormat(args), value, redisKeyWrapper.getTimeout(), TimeUnit.SECONDS);
    }
    
    /**
     * 设置
     *
     * @param key     key
     * @param value   value
     * @param seconds seconds
     */
    public void set(String key, String value, long seconds) {
        operations.set(key, value, seconds, TimeUnit.SECONDS);
    }
    
    /**
     * 设置
     *
     * @param key     key
     * @param value   value
     * @param seconds seconds
     * @param unit    unit
     */
    public void set(String key, String value, long seconds, TimeUnit unit) {
        operations.set(key, value, seconds, unit);
    }
    
    /**
     * 自增
     *
     * @param key key
     * @return 增长后的值
     */
    public Long incr(String key) {
        return operations.increment(key);
    }
    
}
