package com.yidian.galaxy.redis.entity;

/**
 * RedisKey
 *
 * @author changshuai.yuan create on 2024/1/25 10:23
 */
public interface RedisKeyWrapper {
    
    String getKey();
    
    long getTimeOut();
    
    String keyFormat(Object... args);
    
}
