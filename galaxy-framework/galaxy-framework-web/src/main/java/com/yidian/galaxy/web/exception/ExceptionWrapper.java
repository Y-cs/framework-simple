package com.yidian.galaxy.web.exception;

/**
 * 错误信息枚举的包装接口
 *
 * @author changshuai.yuan create on 2024/1/18 16:34
 */
public interface ExceptionWrapper {
    
    /**
     * 获取错误Code
     *
     * @return code
     */
    int getCode();
    
    /**
     * 获取错误信息
     *
     * @return 错误信息
     */
    String getMsg();
    
}
