package com.yidian.galaxy.app.consts;

import com.yidian.galaxy.redis.entity.RedisKeyWrapper;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * RedisKey
 *
 * @author changshuai.yuan create on 2024/1/23 9:41
 */
@Getter
@RequiredArgsConstructor
public enum RedisKey implements RedisKeyWrapper {
    
    APP_USER("app_user:%s", 60 * 60 * 24 * 7),
    APP_USER_CAPTCHA("app_user_captcha:%s", 5 * 60);
    
    private final String key;
    
    private final long timeout;
    
}
