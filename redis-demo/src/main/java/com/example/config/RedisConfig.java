package com.example.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Javen
 * @date 2022/2/1
 */
@Configuration
public class RedisConfig{

    @Autowired
    private RedisTemplate redisTemplate;

//    @Value("${spring.redis.host}")
//    private String host;
//    @Value("${spring.redis.port}")
//    private int port;
//    @Value("${spring.redis.password}")
//    private String password;
//    @Value("${spring.redis.pool.max-total}")
//    private int maxTotal;
//    @Value("${spring.redis.pool.max-wait}")
//    private int maxWait;
//    @Value("${spring.redis.pool.max-idle}")
//    private int maxIdle;
//    @Value("${spring.redis.pool.min-idle}")
//    private int minIdle;

//    public JedisPoolConfig poolConfig(){
//        JedisPoolConfig poolConfig = new JedisPoolConfig();
//        poolConfig.setMaxIdle(maxIdle);
//        poolConfig.setMaxWaitMillis(maxWait);
//        poolConfig.setMinIdle(minIdle);
//        poolConfig.setMaxTotal(maxTotal);
//        poolConfig.setTestOnBorrow(true);
//        poolConfig.setTestWhileIdle(true);
//        return poolConfig;
//    }
//
//    @Bean
//    public RedisConnectionFactory redisConnectionFactory() {
//        RedisStandaloneCome(host);
//        configuration.setPort(port);
//        configuration.setDatabase(0);
//        configuration.setPassword(password);
//
//        JedisClientConfiguration.JedisPoolingClientConfigurationBuilder jpcb =
//                (JedisClientConfiguration.JedisPoolingClientConfigurationBuilder)JedisClientConfiguration.builder();
//
//        jpcb.poolConfig(poolConfig());
//
//        JedisClientConfiguration jedisClientConfiguration = jpcb.build();
//        return new JedisConnectionFactory(configuration, jedisClientConfiguration);
//    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new JdkSerializationRedisSerializer());
        redisTemplate.setHashValueSerializer(new JdkSerializationRedisSerializer());
        return redisTemplate;
    }
//
//
    @Bean
    public StringRedisTemplate stringRedisTemplate(RedisConnectionFactory factory) {
        StringRedisTemplate stringRedisTemplate = new StringRedisTemplate();
        stringRedisTemplate.setConnectionFactory(factory);
        return stringRedisTemplate;
    }
//

    @Bean
    RedisCacheWriter writer() {
        return RedisCacheWriter.nonLockingRedisCacheWriter(redisTemplate.getConnectionFactory());
    }

    @Bean
    public RedisCacheManager cacheManager() {
        Map<String, RedisCacheConfiguration> configurationMap = new HashMap<>();
        configurationMap.put("id", RedisCacheConfiguration
                .defaultCacheConfig().entryTtl(Duration.ofSeconds(10)));
        return RedisCacheManager.builder(writer())
                .initialCacheNames(configurationMap.keySet())
                .withInitialCacheConfigurations(configurationMap)
                .build();
//        return RedisCacheManager.create(connectionFactory);
    }
}
