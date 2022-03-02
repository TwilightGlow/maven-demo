package com.example.service;

/**
 * @author Javen
 * @date 2022/2/15
 */
public interface KillService {

    boolean killGoods(Long id, Integer num) throws InterruptedException;

    boolean muliLock(Long id1, Long id2, Long id3) throws InterruptedException;
}
