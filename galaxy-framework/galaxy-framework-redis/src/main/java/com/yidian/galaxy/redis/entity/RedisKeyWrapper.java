package com.yidian.galaxy.redis.entity;

/**
 * RedisKey
 *
 * @author changshuai.yuan create on 2024/1/25 10:23
 */
public interface RedisKeyWrapper {
    
    String getKey();
    
    long getTimeout();
    
    default String keyFormat(Object... args) {
        return String.format(getKey(), args);
    }
    
}
