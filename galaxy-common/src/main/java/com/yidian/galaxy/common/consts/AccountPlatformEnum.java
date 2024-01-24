package com.yidian.galaxy.common.consts;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * AccountPlatformEnum
 *
 * @author changshuai.yuan create on 2024/1/23 16:35
 */
@Getter
@RequiredArgsConstructor
public enum AccountPlatformEnum {
    
    APP(1),
    
    MANAGEMENT(2),
    
    OPERATE(4);
    
    @EnumValue
    private final int code;
    
}
