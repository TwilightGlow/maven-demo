package com.example.controller;

import com.example.service.KillService;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RCountDownLatch;
import org.redisson.api.RSemaphore;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Javen
 * @date 2022/2/15
 */
@Slf4j
@RestController
@RequestMapping("lock")
public class LockController {

    @Autowired
    private RedissonClient redissonClient;

    @Autowired
    private KillService killService;

    @RequestMapping("kill")
    public boolean kill(Long id, Integer num) throws InterruptedException {
        return killService.killGoods(id, num);
    }

    @RequestMapping("redLock")
    public boolean redLock(Long id1, Long id2, Long id3) throws InterruptedException {
        return killService.muliLock(id1, id2, id3);
    }

    // CountDownLatch
    @GetMapping("/lockDoor")
    @ResponseBody
    public String lockDoor() throws InterruptedException {
        RCountDownLatch door = redissonClient.getCountDownLatch("door");
        door.trySetCount(5);
        door.await();
        return "放假了";
    }

    @GetMapping("/go/{id}")
    public String go(@PathVariable("id") Long id){
        RCountDownLatch door = redissonClient.getCountDownLatch("door");
        door.countDown();;
        return id + "班的人都走了";
    }

    // Semaphore
    @GetMapping("/park")
    public String park() throws InterruptedException {
        RSemaphore park = redissonClient.getSemaphore("park");
        park.acquire();  // 获取一个信号，占一个车位
        return "ok";
    }

    @GetMapping("/leavePark")
    public String leavePark(){
        RSemaphore park = redissonClient.getSemaphore("park");
        park.release();  // 释放一个车位
        return "ok";
    }
}
