package com.example.test;

import com.example.service.RedisService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.concurrent.TimeUnit;

/**
 * @author Javen
 * @date 2022/2/1
 */
@SpringBootTest
public class RedisTest {

    //注入redisTemplate
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private RedisTemplate<String, String> redisTemplate1;

    @Autowired
    private RedisService redisService;

    /**
     * 存入缓存键 key：value
     * 			first ：siwei
     * 			second：siweiWu （30秒过期时间）
     */
    @Test
    public void setRedis() {
        //缓存中最常用的方法
        stringRedisTemplate.opsForValue().set("first","javen");
        //设置缓存过期时间为30   单位：秒　　　　
        //关于TimeUnit下面有部分源码截图
        stringRedisTemplate.opsForValue().set("second","sophia",30, TimeUnit.SECONDS);
        System.out.println("存入缓存成功");
    }


    /**
     * 根据key 获取 value
     */
    @Test
    public void getRedis(){
        String first = stringRedisTemplate.opsForValue().get("first");
        String second = stringRedisTemplate.opsForValue().get("second");
        Boolean ifAbsent = stringRedisTemplate.opsForValue().setIfAbsent("third", "3");

        System.out.println("取出缓存中first的数据是:"+first);
        System.out.println("取出缓存中second的数据是:"+second);
        System.out.println(ifAbsent);

    }


    /**
     * 根据key 删除缓存
     */
    @Test
    public void delRedis() {
        //根据key删除缓存
        Boolean first = stringRedisTemplate.delete("first");

        System.out.println("是否删除成功:"+first);
    }

    @Test
    public void testRedisList() {
//        redisService.cacheList("list", "1");
//        redisService.cacheList("list", Arrays.asList("2", "3", "4", "5"));
        System.out.println(redisService.getListSize("list"));
//        redisService.removeList("list");
        System.out.println(redisService.getList("list", 0, 5));
//        redisService.cacheList("list", "7");
        System.out.println(redisService.getOneFromList("list", 2));
    }

    @Test
    @SuppressWarnings("unchecked")
    // https://blog.csdn.net/notsaltedfish/article/details/75948281
    public void testRedisGenerics() {
        // RedisTemplate使用的是 JdkSerializationRedisSerializer序列化，适合对象类型数据
        redisTemplate.opsForValue().set("first", "first");
        // StringRedisTemplate使用的是 StringRedisSerializer序列化，适合字符串类型数据
        redisTemplate1.opsForValue().set("second", "second");
        stringRedisTemplate.opsForValue().set("third", "third");
        System.out.println(redisTemplate.opsForValue().get("first"));
        System.out.println(redisTemplate1.opsForValue().get("second"));
        System.out.println(stringRedisTemplate.opsForValue().get("third"));
        System.out.println(redisTemplate.delete("first"));
        System.out.println(redisTemplate1.delete("second"));
        System.out.println(stringRedisTemplate.delete("third"));
    }
}
