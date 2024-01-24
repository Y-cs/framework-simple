package com.yidian.galaxy.web.exception;

import lombok.Getter;

/**
 * 系统错误.
 *
 * @author changshuai.yuan DateTime: 2023/12/11 10:47
 */
@Getter
public enum SystemExceptionEnum implements ExceptionWrapper {
    
    //系统级错误1-100
    FAIL(-1, "服务器错误"),
    PARAM_ERROR(-2, "参数错误"),
    OUT_EXCEPTION(-10, "第三方系统错误"),
    //用户及权限相关错误100+
    NOT_LOGIN(-100, "没有登录"),
    LOGIN_TIME_OUT(-101, "登录过期");
    
    //业务错误1000+
    
    private final int code;
    
    private final String msg;
    
    SystemExceptionEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
