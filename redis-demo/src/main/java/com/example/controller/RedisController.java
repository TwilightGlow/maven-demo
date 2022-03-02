package com.example.controller;

import com.example.service.KillService;
import com.example.util.RedisUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author Javen
 * @date 2022/2/1
 */

@Slf4j
@RestController
@RequestMapping("redis")
public class RedisController {

    @Autowired
    private RedisUtils redisUtils;

    @Autowired
    private KillService killService;

    @RequestMapping(value = "/hello/{id}")
    public String hello(@PathVariable(value = "id") String id) {
        //查询缓存中是否存在
        boolean hasKey = redisUtils.exists(id);
        String str = "";
        if (hasKey) {
            //获取缓存
            Object object = redisUtils.get(id);
            log.info("从缓存获取的数据" + object);
            str = object.toString();
        } else {
            //从数据库中获取信息
            log.info("从数据库中获取数据");
            //数据插入缓存（set中的参数含义：key值，user对象，缓存存在时间10（long类型），时间单位）
            redisUtils.set(id, id + " - " + new Date(), 10L, TimeUnit.MINUTES);
            log.info("数据插入缓存" + str);
        }
        return str;
    }

    @RequestMapping("set")
    public boolean redisSet(String key, String value) {
        log.info("123");
        //return redisUtil.set(key,userEntity,ExpireTime);
        return redisUtils.set(key, value);
    }

    @RequestMapping("get")
    public Object redisGet(String key) {
        return redisUtils.get(key);
    }

    @RequestMapping("expire")
    public boolean expire(String key) {
        return redisUtils.expire(key, 60);
    }

    @RequestMapping("kill")
    public boolean kill(Long id, Integer num) throws InterruptedException {
        return killService.killGoods(id, num);
    }
}
