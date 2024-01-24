package com.yidian.galaxy.cornerstone.lambda;

import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

/**
 * 收集扩展
 *
 * @author changshuai.yuan create on 2024/1/18 16:34
 **/
public class CollectExtend<T, A, R> implements Collector<T, A, R> {
    
    private Supplier<A> supplier;
    
    private BiConsumer<A, T> accumulator;
    
    private BinaryOperator<A> combiner;
    
    private Function<A, R> finisher;
    
    private Set<Characteristics> characteristics;
    
    protected CollectExtend(Supplier<A> supplier, BiConsumer<A, T> accumulator, BinaryOperator<A> combiner,
            Function<A, R> finisher, Set<Characteristics> characteristics) {
        this.supplier = supplier;
        this.accumulator = accumulator;
        this.combiner = combiner;
        this.finisher = finisher;
        this.characteristics = characteristics;
    }
    
    protected CollectExtend() {
    }
    
    @Override
    public BiConsumer<A, T> accumulator() {
        return accumulator;
    }
    
    @Override
    public Supplier<A> supplier() {
        return supplier;
    }
    
    @Override
    public BinaryOperator<A> combiner() {
        return combiner;
    }
    
    @Override
    public Function<A, R> finisher() {
        return finisher;
    }
    
    @Override
    public Set<Characteristics> characteristics() {
        return characteristics;
    }
}
