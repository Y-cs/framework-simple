package com.yidian.galaxy.cornerstone.lambda;

/**
 * 收集合并函数
 *
 * @author changshuai.yuan create on 2024/1/18 16:34
 */
public final class CollectMerge {
    
    public static <T> T byFirst(T t1, T t2) {
        return t1;
    }
    
    public static <T> T byLast(T t1, T t2) {
        return t2;
    }
    
}
