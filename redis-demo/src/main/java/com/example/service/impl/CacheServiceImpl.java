package com.example.service.impl;

import com.example.service.CacheService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * @author Javen
 * @date 2022/2/8
 */
@Service
public class CacheServiceImpl implements CacheService {

    @Override
    @Cacheable(cacheNames = "id", key = "#id")
    public String getById(String id) {
        sleep();
        return "Cache";
    }

    private void sleep() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Sleep for 3 s");
    }
}
