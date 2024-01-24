package com.yidian.galaxy.redis.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.config.SingleServerConfig;
import org.redisson.spring.data.connection.RedissonConnectionFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Redisson配置
 *
 * @author changshuai.yuan DateTime: 2023/12/26 19:40
 */
@Configuration
@ConditionalOnBean(RedisProperties.class)
public class RedissonConfig {
    
    @Bean
    public RedissonConnectionFactory redissonConnectionFactory(RedissonClient redisson) {
        return new RedissonConnectionFactory(redisson);
    }
    
    @Bean(destroyMethod = "shutdown")
    public RedissonClient redisson(RedisProperties redisProperties) {
        Config config = new Config();
        SingleServerConfig singleServerConfig = config.useSingleServer();
        String redisUrl = String.format("redis://%s:%s", redisProperties.getHost(), redisProperties.getPort());
        singleServerConfig.setAddress(redisUrl);
        singleServerConfig.setPassword(redisProperties.getPassword());
        singleServerConfig.setDatabase(redisProperties.getDatabase());
        return Redisson.create(config);
    }
    
}