package com.yidian.galaxy.app.service.impl;

import cn.hutool.core.util.IdcardUtil;
import cn.hutool.core.util.PhoneUtil;
import cn.hutool.crypto.digest.DigestUtil;
import com.yidian.galaxy.app.consts.RedisKey;
import com.yidian.galaxy.app.entity.dto.AppUserInfoDto;
import com.yidian.galaxy.app.entity.vo.AppUserLoginVo;
import com.yidian.galaxy.app.entity.vo.AppUserRegisterVo;
import com.yidian.galaxy.app.service.UserService;
import com.yidian.galaxy.common.biz.UserBiz;
import com.yidian.galaxy.common.consts.AccountPlatformEnum;
import com.yidian.galaxy.common.domain.AppUserDo;
import com.yidian.galaxy.redis.support.RedisSupport;
import com.yidian.galaxy.web.entity.JwtParam;
import com.yidian.galaxy.web.exception.Asserts;
import com.yidian.galaxy.web.exception.BusinessException;
import com.yidian.galaxy.web.support.JwtSupport;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * UserServiceImpl
 *
 * @author changshuai.yuan create on 2024/1/22 17:32
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    
    private final UserBiz userBiz;
    
    private final RedisSupport redisSupport;
    
    @Override
    public AppUserInfoDto login(AppUserLoginVo appUserLoginVO) {
        AppUserDo appUser;
        if (AppUserLoginVo.LoginTypeEnum.PHONE_PASSWORD == appUserLoginVO.getType()) {
            //手机号密码登录
            final String phone = appUserLoginVO.getPhone();
            //校验系统有没有开通账户
            Asserts.isFalse(userBiz.checkPhonePlatform(phone, AccountPlatformEnum.APP))
                    .throwBusinessException("用户不存在");
            //查询用户信息
            appUser = Optional.ofNullable(userBiz.findAppUserByPhone(phone))
                    .orElseThrow(() -> new BusinessException("用户不存在"));
            //验证密码
            final String password = DigestUtil.sha256Hex(appUserLoginVO.getPassword());
            Asserts.isFalse(appUser.getPassword().equals(password)).throwBusinessException("密码错误");
        } else {
            throw new BusinessException("不支持的登陆方式");
        }
        Asserts.isFalse(appUser.getActive()).throwBusinessException("用户停用");
        String token = JwtSupport.createJwtToken(
                new JwtParam().setAccountId(appUser.getAccountId()).setUserId(appUser.getId())
                        .setSystem(AccountPlatformEnum.APP.getCode()));
        //补充用户信息
        final AppUserInfoDto appUserInfoDto = new AppUserInfoDto().setPhone(appUser.getPhone()).setSex(appUser.getSex())
                .setHeadImg(appUser.getHeadImg()).setNikeName(appUser.getNickName())
                .setInviteCode(appUser.getInviteCode()).setAccountId(appUser.getId()).setUserId(appUser.getId())
                .setAuthenticationStatus(appUser.getAuthenticationStatus()).setName(appUser.getName())
                .setIdCard(IdcardUtil.hide(appUser.getIdCard(), 2, 15)).setToken(token)
                .setCreateTime(appUser.getCreateTime());
        //缓存用户信息
        redisSupport.objectRedisOperations()
                .set(RedisKey.APP_USER.getPrefix(), appUserInfoDto, RedisKey.APP_USER.getTimeout());
        return appUserInfoDto;
    }
    
    @Override
    public void register(AppUserRegisterVo userRegisterVO) {
        String phone = userRegisterVO.getPhone();
        Asserts.isFalse(PhoneUtil.isPhone(phone)).throwBusinessException("手机号格式错误");
        String captcha = redisSupport.stringRedisOperations().get(RedisKey.APP_REGISTER_CAPTCHA.getPrefix() + phone);
        Asserts.isFalse(StringUtils.isNotBlank(captcha)).throwBusinessException("验证码过期");
        Asserts.isFalse(captcha.equals(userRegisterVO.getVerificationCode())).throwBusinessException("验证码错误");
        Asserts.isTrue(userBiz.checkAppUserExist(phone)).throwBusinessException("用户已注册");
        String inviteCode = userRegisterVO.getInviteCode();
        Long inviteUserId = null;
        if (StringUtils.isNotBlank(inviteCode)) {
            inviteUserId = userBiz.findInviteUserId(inviteCode);
            Asserts.isNull(inviteUserId).throwBusinessException("邀请人不存在");
        }
        userBiz.registerAppUser(phone, DigestUtil.sha256Hex(userRegisterVO.getPassword()), inviteUserId);
    }
}
