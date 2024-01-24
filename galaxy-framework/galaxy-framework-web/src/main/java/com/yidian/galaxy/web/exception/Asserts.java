package com.yidian.galaxy.web.exception;

/**
 * 校验对象
 *
 * @author changshuai.yuan DateTime: 2023/12/29 11:01
 */
public class Asserts {
    
    /**
     * 是否为空
     *
     * @param obj 对象
     * @return 错误函数
     */
    public static ThrowExceptionConsumer isNull(Object obj) {
        return (message) -> {
            if (obj == null) {
                throw new BusinessException(message);
            }
        };
    }
    
    /**
     * 真
     * @param condition 条件
     * @return 错误函数
     */
    public static ThrowExceptionConsumer isTrue(boolean condition) {
        return (message) -> {
            if (condition) {
                throw new BusinessException(message);
            }
        };
    }
    
    /**
     * 假
     * @param condition 条件
     * @return 错误函数
     */
    public static ThrowExceptionConsumer isFalse(boolean condition) {
        return (message) -> {
            if (!condition) {
                throw new BusinessException(message);
            }
        };
    }
    
}
