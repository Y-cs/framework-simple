package com.yidian.galaxy.redis.support;

import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Redis hash的支持
 *
 * @author changshuai.yuan create on 2024/1/19 14:43
 */
public class HashRedisOperations {
    
    private final HashOperations<String, Object, Object> operations;
    
    public HashRedisOperations(RedisTemplate<String, Object> redisTemplate) {
        this.operations = redisTemplate.opsForHash();
    }
    
    /**
     * hSet
     *
     * @param key   key
     * @param field field
     * @param value value
     */
    public void hSet(String key, Object field, Object value) {
        this.operations.put(key, field, value);
    }
    
    /**
     * hmSet
     *
     * @param key         key
     * @param fieldValues fieldValues
     */
    public void hmSet(String key, Map<Object, Object> fieldValues) {
        this.operations.putAll(key, fieldValues);
    }
    
    /**
     * 获取单个
     *
     * @param key   key
     * @param field inside key
     * @param <V>   value class
     * @return value
     */
    @SuppressWarnings("unchecked")
    public <V> V hGet(String key, Object field) {
        return (V) this.operations.get(key, field);
    }
    
    /**
     * 批量获取值
     *
     * @param key key
     * @param ks  inside keys
     * @param <V> 值类型
     * @return 值的集合
     */
    @SuppressWarnings("unchecked")
    public <V> List<V> hGets(String key, Collection<Object> ks) {
        return (List<V>) this.operations.multiGet(key, ks);
    }
    
    /**
     * 获取全部
     *
     * @param key key
     * @param <K> key class
     * @param <V> value class
     * @return 全部的Map
     */
    @SuppressWarnings("unchecked")
    public <K, V> Map<K, V> hGetAll(String key) {
        return (Map<K, V>) this.operations.entries(key);
    }
    
    /**
     * hash内删除
     *
     * @param key    hash key
     * @param fields inside hash key
     */
    public void hDelete(String key, Object... fields) {
        this.operations.delete(key, fields);
    }
    
}
