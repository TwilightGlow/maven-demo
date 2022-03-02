package com.example.service.impl;

import com.example.service.KillService;
import lombok.extern.slf4j.Slf4j;
import org.redisson.RedissonMultiLock;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @author Javen
 * @date 2022/2/15
 */
@Slf4j
@Service
public class KillServiceImpl implements KillService {

    @Autowired
    private RedissonClient redissonClient;

    private static final String LOCK_KEY = "lock";

    @Override
    // 可重入锁 - 同一个线程可重入 - 加了几次锁就要释放几次
    public boolean killGoods(Long id, Integer num) throws InterruptedException {
        String key = LOCK_KEY + id;
        RLock lock = redissonClient.getLock(key);
        // 另一个线程拿到锁返回true，不然返回false
        boolean tryLock = lock.tryLock(5, 10, TimeUnit.SECONDS);
        if (!tryLock) {
            log.info("线程" + Thread.currentThread().getName() + "无法获取锁：" + key);
            return false;
        }
        try {
//            // 另一个线程拿不到锁就在这里阻塞
//            lock.lock(30L, TimeUnit.SECONDS);
            log.info("线程" + Thread.currentThread().getName() + "获取锁：" + key);
            // do some business here
            Thread.sleep(9000);
            log.info("线程" + Thread.currentThread().getName() + "操作库存减" + num);
        } catch (Exception e) {
            log.info("线程" + Thread.currentThread().getName() + "系统异常");
            return false;
        } finally {
            // 这里Redisson已经实现了判断删除锁时的原子操作，如果是redis一般用Lua脚本实现
            if (lock.isLocked() && lock.isHeldByCurrentThread()) {
                lock.unlock();
                log.info("线程" + Thread.currentThread().getName() + "释放锁：" + key);
            }
        }
        return true;
    }

    // 联锁 multiLock - 必须一起获取，一起释放
    // 红锁 redLock - 超过半数获取，用完释放(用于Redis集群，多个主节点的场景)
    @Override
    public boolean muliLock(Long id1, Long id2, Long id3) throws InterruptedException {
        RLock lock1 = redissonClient.getLock(LOCK_KEY + id1);
        RLock lock2 = redissonClient.getLock(LOCK_KEY + id2);
        RLock lock3 = redissonClient.getLock(LOCK_KEY + id3);
        RedissonMultiLock multiLock = new RedissonMultiLock(lock1, lock2, lock3);
        boolean tryLock = multiLock.tryLock(10, 30, TimeUnit.SECONDS);
        if (!tryLock) {
            log.info("线程" + Thread.currentThread().getName() + "无法获取锁：" + multiLock);
            return false;
        }
        try {
            log.info("线程" + Thread.currentThread().getName() + "获取multi锁：" + multiLock);
            Thread.sleep(10000);
        } catch (Exception e) {
            log.info("线程" + Thread.currentThread().getName() + "系统异常");
            return false;
        } finally {
            multiLock.unlock();
            log.info("线程" + Thread.currentThread().getName() + "释放multi锁：" + multiLock);
        }
        return true;
    }
}
