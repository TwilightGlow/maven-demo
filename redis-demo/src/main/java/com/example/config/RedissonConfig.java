//package com.example.config;
//
//import org.redisson.Redisson;
//import org.redisson.api.RedissonClient;
//import org.redisson.config.Config;
//import org.redisson.spring.data.connection.RedissonConnectionFactory;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.io.ClassPathResource;
//
//import java.io.IOException;
//
///**
// * @author Javen
// * @date 2022/2/15
// */
//@Configuration
//public class RedissonConfig {
//
//    @Bean
//    public RedissonConnectionFactory redissonConnectionFactory(RedissonClient redisson) {
//        return new RedissonConnectionFactory(redisson);
//    }
//
//    @Bean(destroyMethod="shutdown")
//    public RedissonClient redisson() throws IOException {
//        return Redisson.create(
//                Config.fromYAML(new ClassPathResource("redisson-single.yaml").getInputStream()));
//    }
//
//}
