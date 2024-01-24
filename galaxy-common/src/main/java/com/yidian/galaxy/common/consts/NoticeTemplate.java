package com.yidian.galaxy.common.consts;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * NoticeTemplate
 *
 * @author changshuai.yuan create on 2024/1/24 18:04
 */
public interface NoticeTemplate {
    
    enum UsesEnum {
        APP_USER_REGISTER
    }
    
    @Getter
    @RequiredArgsConstructor
    enum ChannelEnum {
        SMS_DXB(1, "短信宝");
        
        @EnumValue
        private final int code;
        
        private final String msg;
    }
    
}
