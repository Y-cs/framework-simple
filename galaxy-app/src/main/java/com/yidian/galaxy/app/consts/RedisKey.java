package com.yidian.galaxy.app.consts;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * RedisKey
 *
 * @author changshuai.yuan create on 2024/1/23 9:41
 */
@Getter
@RequiredArgsConstructor
public enum RedisKey {
    
    APP_USER("app_user:", 60 * 60 * 24 * 7),
    APP_REGISTER_CAPTCHA("captcha:app_register:", 5 * 60);
    
    private final String prefix;
    
    private final long timeout;
    
}
