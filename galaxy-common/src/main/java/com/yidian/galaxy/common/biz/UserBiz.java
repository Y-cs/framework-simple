package com.yidian.galaxy.common.biz;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.yidian.galaxy.common.consts.AccountPlatformEnum;
import com.yidian.galaxy.common.domain.AppUserDo;
import com.yidian.galaxy.common.mapper.AccountMapper;
import com.yidian.galaxy.common.mapper.AppUserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * UserBiz
 *
 * @author changshuai.yuan create on 2024/1/23 15:09
 */
@Component
@RequiredArgsConstructor
public class UserBiz {
    
    private final AccountMapper accountMapper;
    
    private final AppUserMapper appUserMapper;
    
    public boolean checkPhonePlatform(String phone, AccountPlatformEnum platformEnum) {
        return Optional.ofNullable(accountMapper.checkPhonePlatform(phone, platformEnum)).orElse(false);
    }
    
    public AppUserDo findAppUserByPhone(String phone) {
        return appUserMapper.selectOne(
                new LambdaQueryWrapper<AppUserDo>().eq(AppUserDo::isDel, false).eq(AppUserDo::getPhone, phone));
    }
    
}
