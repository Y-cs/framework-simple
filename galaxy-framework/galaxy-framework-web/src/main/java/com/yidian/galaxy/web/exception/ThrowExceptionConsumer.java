package com.yidian.galaxy.web.exception;

/**
 * 抛出错误函数接口
 *
 * @author changshuai.yuan DateTime: 2023/12/11 10:47
 */
@FunctionalInterface
public interface ThrowExceptionConsumer {
    
    void throwBusinessException(String message);
    
}
