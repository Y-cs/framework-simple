package com.yidian.galaxy.common.consts;

import com.yidian.galaxy.redis.entity.RedisKeyWrapper;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * RedisKey
 *
 * @author changshuai.yuan create on 2024/1/24 18:12
 */
@Getter
@RequiredArgsConstructor
public enum CommonRedisKey implements RedisKeyWrapper {
    
    NOTICE_TEMPLATE("notice_template", -1L);
    
    private final String key;
    
    private final long timeout;
    
}
