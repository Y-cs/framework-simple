package com.yidian.galaxy.common.consts;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * RedisKey
 *
 * @author changshuai.yuan create on 2024/1/24 18:12
 */
@Getter
@RequiredArgsConstructor
public enum RedisKey {
    
    NOTICE_TEMPLATE("notice_template", -1L);
    
    private final String prefix;
    
    private final long timeout;
    
}
