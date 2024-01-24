package com.yidian.galaxy.web.entity;

import lombok.Value;

import java.security.Principal;
import java.time.LocalDateTime;

/**
 * 用户Session
 *
 * @author changshuai.yuan create on 2024/1/19 17:45
 */
@Value(staticConstructor = "of")
public class UserSession implements Principal {
    
    long accountId;
    
    long userId;
    
    String phone;
    
    String nikeName;
    
    String name;
    
    LocalDateTime createTime;
    
}
