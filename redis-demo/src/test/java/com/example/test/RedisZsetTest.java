package com.example.test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;

/**
 * @author Javen
 * @date 2022/2/2
 */
@SpringBootTest
public class RedisZsetTest {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    private static final String prefix = "test:zset:";

    @Test
    public void testZset() {
        ZSetOperations<String, String> opsForZSet = redisTemplate.opsForZSet();
        System.out.println(opsForZSet.add(prefix + "rank", "1", 1));
        System.out.println(opsForZSet.add(prefix + "rank", "0", 0));
        System.out.println(opsForZSet.add(prefix + "rank", "2", 2));
        System.out.println(opsForZSet.add(prefix + "rank", "Javen", 1.5));
        System.out.println("集合大小为：" + opsForZSet.zCard(prefix + "rank"));
        System.out.println("Javen的排名为：" + opsForZSet.rank(prefix + "rank", "Javen"));
        System.out.println("根据排序位置获取数据：" + opsForZSet.range(prefix + "rank", 1, 2));
        System.out.println("根据score获取数据：" + opsForZSet.rangeByScore(prefix + "rank", 1, 2));
    }

}
