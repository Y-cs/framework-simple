package com.yidian.galaxy.app.service.impl;

import cn.hutool.core.util.RandomUtil;
import com.yidian.galaxy.app.consts.AppRedisKey;
import com.yidian.galaxy.app.service.SmsService;
import com.yidian.galaxy.common.consts.NoticeTemplateConst;
import com.yidian.galaxy.common.support.NoticeSupport;
import com.yidian.galaxy.redis.support.RedisSupport;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * SmsServiceImpl
 *
 * @author changshuai.yuan create on 2024/1/25 15:05
 */
@Service
@RequiredArgsConstructor
public class SmsServiceImpl implements SmsService {
    
    private final NoticeSupport noticeSupport;
    
    private final RedisSupport redisSupport;
    
    @Override
    public boolean captcha(String phone) {
        String captcha = RandomUtil.randomNumbers(6);
        redisSupport.stringRedisOperations().set(captcha, AppRedisKey.APP_USER_CAPTCHA, phone);
        return noticeSupport.pushMsg(NoticeTemplateConst.UsesEnum.APP_USER_CAPTCHA, phone, captcha);
    }
    
}
