package com.yidian.galaxy.common.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.yidian.galaxy.web.entity.BaseModel;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * AppUserDo
 *
 * @author changshuai.yuan create on 2024/1/23 16:26
 */
@Data
@TableName("app_user")
public class AppUserDo extends BaseModel {
    
    /**
     * id
     */
    private Long id;
    
    /**
     * accountId
     */
    private Long accountId;
    
    /**
     * 上级标识
     */
    private String usn;
    
    /**
     * 手机号
     */
    private String phone;
    
    private String password;
    
    /**
     * 昵称
     */
    private String nickName;
    
    /**
     * 性别 0-未知，1-男，2-女
     */
    private Integer sex;
    
    /**
     * 头像
     */
    private String headImg;
    
    /**
     * 自己的邀请码
     */
    private String inviteCode;
    
    /**
     * 邀请人id
     */
    private Long inviteUserId;
    
    /**
     * 姓名
     */
    private String name;
    
    /**
     * 身份证号
     */
    private String idCard;
    
    /**
     * 认证提交时间
     */
    private LocalDateTime submitAuthenticationTime;
    
    /**
     * 认证时间
     */
    private LocalDateTime authenticationTime;
    
    /**
     * 实名认证状态，0-false，1-true
     */
    private Integer authenticationStatus;
    
    /**
     * 最后登录时间
     */
    private LocalDateTime lastLoginTime;
    
    /**
     * 是否启用
     */
    private Boolean active;
}