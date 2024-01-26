package com.yidian.galaxy.common.biz;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.yidian.galaxy.common.consts.AccountPlatformEnum;
import com.yidian.galaxy.common.consts.AppUserNatureEnum;
import com.yidian.galaxy.common.consts.AuthenticationStatusEnum;
import com.yidian.galaxy.common.consts.UserSexEnum;
import com.yidian.galaxy.common.domain.AccountDo;
import com.yidian.galaxy.common.domain.AppUserDo;
import com.yidian.galaxy.common.domain.InviteRecordDo;
import com.yidian.galaxy.common.entity.vo.AppUserUpdateVo;
import com.yidian.galaxy.common.mapper.AccountMapper;
import com.yidian.galaxy.common.mapper.AppUserMapper;
import com.yidian.galaxy.common.mapper.InviteRecordMapper;
import com.yidian.galaxy.web.support.SqidsSupport;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Objects;
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
    
    private final InviteRecordMapper inviteRecordMapper;
    
    public boolean checkPhonePlatform(String phone, AccountPlatformEnum platformEnum) {
        return Optional.ofNullable(accountMapper.checkPhonePlatform(phone, platformEnum)).orElse(false);
    }
    
    public AppUserDo findAppUserByPhone(String phone) {
        return appUserMapper.selectOne(
                new LambdaQueryWrapper<AppUserDo>().eq(AppUserDo::isDel, false).eq(AppUserDo::getPhone, phone));
    }
    
    /**
     * 注册App用户
     *
     * @param phone        手机号
     * @param password     加密后的密码
     * @param inviteUserId 邀请人Id
     */
    @Transactional(rollbackFor = Exception.class)
    public void registerAppUser(String phone, String password, Long inviteUserId) {
        AccountDo accountDo = Optional.ofNullable(
                        accountMapper.selectOne(new LambdaQueryWrapper<AccountDo>().eq(AccountDo::getPhone, phone)))
                .orElse(new AccountDo());
        if (accountDo.getId() == null) {
            accountDo.init();
            accountDo.setPhone(phone);
            accountDo.setPlatform(AccountPlatformEnum.APP.getCode());
            accountMapper.insert(accountDo);
        } else {
            accountDo.update();
            accountDo.setPlatform(accountDo.getPlatform() | AccountPlatformEnum.APP.getCode());
            accountMapper.updateById(accountDo);
        }
        String inviteCode = SqidsSupport.encodeL11ByPhone(phone);
        AppUserDo appUserDo = new AppUserDo();
        appUserDo.setAccountId(accountDo.getId());
        appUserDo.setPhone(phone);
        appUserDo.setPassword(password);
        appUserDo.setNickName("N_" + inviteCode);
        appUserDo.setSex(UserSexEnum.NOT_KNOW.getCode());
        //todo
        //appUserDo.setHeadImg();
        appUserDo.setInviteCode(inviteCode);
        appUserDo.setInviteUserId(inviteUserId);
        appUserDo.setAuthenticationStatus(AuthenticationStatusEnum.NOT_AUTH.getCode());
        appUserDo.setNature(AppUserNatureEnum.APP_USER.getCode());
        appUserDo.setActive(true);
        appUserDo.init();
        appUserMapper.insert(appUserDo);
        
        if (inviteUserId != null) {
            inviteRecordMapper.insert(new InviteRecordDo().setInviterId(inviteUserId).setInviteesId(appUserDo.getId())
                    .setCreateTime(LocalDateTime.now()));
        }
    }
    
    public boolean checkAppUserExist(String phone) {
        return appUserMapper.exists(new LambdaQueryWrapper<AppUserDo>().eq(AppUserDo::getPhone, phone));
    }
    
    /**
     * 根据邀请码查询邀请人
     *
     * @param inviteCode 邀请码
     * @return 邀请人ID
     */
    public Long findInviteUserId(String inviteCode) {
        return Optional.ofNullable(appUserMapper.selectOne(
                        new LambdaQueryWrapper<AppUserDo>().eq(AppUserDo::getInviteCode, inviteCode).select(AppUserDo::getId)))
                .map(AppUserDo::getId).orElse(null);
    }
    
    /**
     * 更新App用户信息
     *
     * @param appUserUpdateVo appUserUpdateVo
     */
    public void updateUserInfo(AppUserUpdateVo appUserUpdateVo) {
        AppUserDo appUserDo = new AppUserDo();
        appUserDo.setId(appUserUpdateVo.getId());
        if (StringUtils.isNotBlank(appUserUpdateVo.getNikeName())) {
            appUserDo.setNickName(appUserUpdateVo.getNikeName());
        }
        if (StringUtils.isNotBlank(appUserUpdateVo.getHeadImg())) {
            appUserDo.setHeadImg(appUserUpdateVo.getHeadImg());
        }
        if (StringUtils.isNotBlank(appUserUpdateVo.getPassword())) {
            appUserDo.setPassword(appUserUpdateVo.getPassword());
        }
        if (!Objects.isNull(appUserUpdateVo.getSex())) {
            appUserDo.setSex(appUserUpdateVo.getSex());
        }
        appUserMapper.updateById(appUserDo);
    }
}
