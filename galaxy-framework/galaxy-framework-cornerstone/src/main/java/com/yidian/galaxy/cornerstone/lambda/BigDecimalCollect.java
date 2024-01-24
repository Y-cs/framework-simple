package com.yidian.galaxy.cornerstone.lambda;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collector;

/**
 * BigDecimal收集类
 *
 * @author changshuai.yuan create on 2024/1/18 16:34
 **/
public class BigDecimalCollect {
    
    static final Set<Collector.Characteristics> CH_NO_ID = Collections.emptySet();
    
    public static Collector<BigDecimal, BigDecimal[], BigDecimal> sum() {
        return BigDecimalCollect.sum(ToBigDecimalFunction.identity());
    }
    
    /**
     * 求和
     *
     * @param mapper 映射关系
     * @param <T>    泛型
     * @return 收集函数
     */
    public static <T> Collector<T, BigDecimal[], BigDecimal> sum(ToBigDecimalFunction<? super T> mapper) {
        return new CollectExtend<>(() -> new BigDecimal[1], (a, t) -> {
            if (a[0] == null) {
                a[0] = BigDecimal.ZERO;
            }
            BigDecimal decimal = mapper.applyAsBigDecimal(t);
            if (decimal != null) {
                a[0] = a[0].add(decimal);
            }
        }, (a, b) -> {
            a[0] = a[0].add(b[0]);
            return a;
        }, a -> a[0], CH_NO_ID);
    }
    
    public static Collector<BigDecimal, ?, BigDecimal> avg() {
        return BigDecimalCollect.avg(ToBigDecimalFunction.identity());
    }
    
    public static <T> Collector<T, ?, BigDecimal> avg(ToBigDecimalFunction<? super T> mapper) {
        return BigDecimalCollect.avg(mapper, 10, RoundingMode.HALF_UP);
    }
    
    public static <T> Collector<T, ?, BigDecimal> avg(ToBigDecimalFunction<? super T> mapper, int scale) {
        return BigDecimalCollect.avg(mapper, scale, RoundingMode.HALF_UP);
    }
    
    /**
     * 平均数
     *
     * @param mapper       映射函数
     * @param scale        保留小数位数
     * @param roundingMode 保留方式
     * @param <T>          泛型
     * @return 收集函数
     */
    public static <T> Collector<T, ?, BigDecimal> avg(ToBigDecimalFunction<? super T> mapper, int scale,
            RoundingMode roundingMode) {
        return new CollectExtend<T, List<BigDecimal>, BigDecimal>(LinkedList::new, (a, t) -> {
            BigDecimal decimal = mapper.applyAsBigDecimal(t);
            if (decimal != null) {
                a.add(decimal);
            }
        }, (a, b) -> {
            a.addAll(b);
            return a;
        }, a -> {
            if (a.isEmpty()) {
                return BigDecimal.ZERO;
            }
            BigDecimal collect = a.stream().collect(BigDecimalCollect.sum());
            return collect.divide(BigDecimal.valueOf(a.size()), scale, roundingMode);
        }, CH_NO_ID);
    }
    
    public static Collector<BigDecimal, ?, BigDecimal> min() {
        return BigDecimalCollect.min(ToBigDecimalFunction.identity());
    }
    
    /**
     * 最小
     *
     * @param mapper 映射函数
     * @param <T>    泛型
     * @return 收集函数
     */
    public static <T> Collector<T, ?, BigDecimal> min(ToBigDecimalFunction<? super T> mapper) {
        return new CollectExtend<>(() -> new BigDecimal[1], (a, t) -> {
            BigDecimal decimal = mapper.applyAsBigDecimal(t);
            if (decimal != null) {
                if (a[0] == null) {
                    a[0] = decimal;
                } else {
                    a[0] = a[0].compareTo(decimal) < 0 ? a[0] : decimal;
                }
            }
        }, (a, b) -> a[0].compareTo(b[0]) < 0 ? a : b, a -> a[0], CH_NO_ID);
    }
    
    public static Collector<BigDecimal, ?, BigDecimal> max() {
        return BigDecimalCollect.max(ToBigDecimalFunction.identity());
    }
    
    /**
     * 最大
     *
     * @param mapper 映射函数
     * @param <T>    泛型
     * @return 收集函数
     */
    public static <T> Collector<T, ?, BigDecimal> max(ToBigDecimalFunction<? super T> mapper) {
        return new CollectExtend<>(() -> new BigDecimal[1], (a, t) -> {
            BigDecimal decimal = mapper.applyAsBigDecimal(t);
            if (decimal != null) {
                if (a[0] == null) {
                    a[0] = decimal;
                } else {
                    a[0] = a[0].compareTo(decimal) > 0 ? a[0] : decimal;
                }
            }
        }, (a, b) -> a[0].compareTo(b[0]) > 0 ? a : b, a -> a[0], CH_NO_ID);
    }
    
}

