package com.jin.common.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.extern.slf4j.Slf4j;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@Configuration
@EnableCaching
@Slf4j
public class RedisConfig extends CachingConfigurerSupport {

	@Value("${spring.redis.host}")
    private String host;

	@Value("${spring.redis.port}")
    private int port;

	@Value("${spring.redis.timeout}")
    private int timeout;

	@Value("${spring.redis.jedis.pool.max-active}")
    private int maxActive;

	@Value("${spring.redis.jedis.pool.max-idle}")
    private int maxIdle;

	@Value("${spring.redis.jedis.pool.min-idle}")
    private int minIdle;

	@Value("${spring.redis.jedis.pool.max-wait}")
    private long maxWaitMillis;

    @Bean
    public JedisPool redisPoolFactory(){
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(maxIdle);
        jedisPoolConfig.setMaxWaitMillis(maxWaitMillis);
        jedisPoolConfig.setMaxTotal(maxActive);
        jedisPoolConfig.setMinIdle(minIdle);
        
        JedisPool jedisPool = new JedisPool(jedisPoolConfig,host,port,timeout);
        log.info("JedisPool注入成功！");
        log.info("redis地址：" + host + ":" + port);
        return  jedisPool;
    }


}