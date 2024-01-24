package com.yidian.galaxy.web.exception;

import java.util.Optional;

/**
 * 业务错误
 *
 * @author changshuai.yuan create on 2024/1/18 16:34
 */
public class BusinessException extends RuntimeException {
    
    private final ExceptionWrapper exceptionWrapper;
    
    public BusinessException() {
        this(SystemExceptionEnum.FAIL);
    }
    
    public BusinessException(Throwable throwable) {
        this(SystemExceptionEnum.FAIL, throwable.getMessage());
    }
    
    public BusinessException(String msg) {
        this(SystemExceptionEnum.FAIL, msg);
    }
    
    public BusinessException(ExceptionWrapper exceptionWrapper) {
        super(exceptionWrapper.getMsg());
        this.exceptionWrapper = exceptionWrapper;
    }
    
    public BusinessException(ExceptionWrapper exceptionWrapper, String msg) {
        super(msg);
        this.exceptionWrapper = exceptionWrapper;
    }
    
    public int getCode() {
        return Optional.ofNullable(exceptionWrapper).orElse(SystemExceptionEnum.FAIL).getCode();
    }
    
    public String getMsg() {
        return Optional.ofNullable(super.getMessage())
                .orElse(Optional.ofNullable(exceptionWrapper).orElse(SystemExceptionEnum.FAIL).getMsg());
    }
    
}