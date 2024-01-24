package com.yidian.galaxy.redis.support;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.concurrent.TimeUnit;

/**
 * Redis 支持
 *
 * @author changshuai.yuan create on 2024/1/19 11:37
 */
@Component
public class RedisSupport {
    
    private final RedisTemplate<String, Object> jsonRedisTemplate;
    
    private final StringRedisOperations stringRedisOperations;
    
    private final ObjectRedisOperations objectRedisOperations;
    
    private final HashRedisOperations hashRedisOperations;
    
    public RedisSupport(StringRedisTemplate stringRedisTemplate,
            @Qualifier("jsonRedisTemplate") RedisTemplate<String, Object> jsonRedisTemplate,
            RedisTemplate<Object, Object> ooRedisTemplate) {
        this.jsonRedisTemplate = jsonRedisTemplate;
        this.stringRedisOperations = new StringRedisOperations(stringRedisTemplate);
        this.objectRedisOperations = new ObjectRedisOperations(jsonRedisTemplate);
        this.hashRedisOperations = new HashRedisOperations(jsonRedisTemplate);
    }
    
    /**
     * 获取String操作对象
     *
     * @return string操作对象
     */
    public StringRedisOperations stringRedisOperations() {
        return this.stringRedisOperations;
    }
    
    /**
     * 获取对象操作对象
     *
     * @return 对象操作对象
     */
    public ObjectRedisOperations objectRedisOperations() {
        return this.objectRedisOperations;
    }
    
    /**
     * 获取hash操作对象
     *
     * @return hash操作对象
     */
    public HashRedisOperations hashRedisOperations() {
        return this.hashRedisOperations;
    }
    
    /*---------------------------通用操作的支持---------------------------------------------------------------------*/
    
    /**
     * 删除
     *
     * @param key key
     */
    public void delete(String key) {
        jsonRedisTemplate.delete(key);
    }
    
    /**
     * 批量删除
     *
     * @param keys keys
     */
    public void deletes(Collection<String> keys) {
        jsonRedisTemplate.delete(keys);
    }
    
    /**
     * 是否存在
     *
     * @param key key
     * @return 是否存在
     */
    public boolean exists(String key) {
        return Boolean.TRUE.equals(jsonRedisTemplate.hasKey(key));
    }
    
    /**
     * 续时
     *
     * @param key     key
     * @param seconds 秒
     */
    public void expire(String key, long seconds) {
        jsonRedisTemplate.expire(key, seconds, TimeUnit.SECONDS);
    }
    
    /**
     * 续时
     *
     * @param key      key
     * @param time     time
     * @param timeUnit 单位
     */
    public void expire(String key, long time, TimeUnit timeUnit) {
        jsonRedisTemplate.expire(key, time, timeUnit);
    }
    
}
