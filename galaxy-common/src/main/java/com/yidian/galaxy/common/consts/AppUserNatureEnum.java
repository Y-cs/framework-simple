package com.yidian.galaxy.common.consts;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * AppUserNatureEnum
 *
 * @author changshuai.yuan create on 2024/1/24 15:01
 */
@Getter
@RequiredArgsConstructor
public enum AppUserNatureEnum {
    
    APP_USER(0),
    TEST_USER(1);
    
    private final int code;
    
    
}
