package com.yidian.galaxy.common.service;

import com.yidian.galaxy.common.consts.NoticeTemplate;

/**
 * SmsService
 *
 * @author changshuai.yuan create on 2024/1/24 17:53
 */
public interface SmsService {
    
    boolean sendSms(String phone, NoticeTemplate.UsesEnum usesEnum, Object... args);
}
