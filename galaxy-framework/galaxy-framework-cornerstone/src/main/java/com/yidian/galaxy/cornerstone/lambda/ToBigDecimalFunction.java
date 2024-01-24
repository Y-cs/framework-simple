package com.yidian.galaxy.cornerstone.lambda;

import java.math.BigDecimal;

/**
 * 转化为BigDecimal 的函数
 * @author changshuai.yuan create on 2024/1/18 16:34
 **/
@FunctionalInterface
public interface ToBigDecimalFunction<T> {
    
    /**
     * 本来就是BigDecimal
     *
     * @return 收集函数
     */
    static ToBigDecimalFunction<BigDecimal> identity() {
        return t -> t;
    }
    
    /**
     * 转化为BigDecimal
     *
     * @param t 入参
     * @return BigDecimal
     */
    BigDecimal applyAsBigDecimal(T t);
    
}