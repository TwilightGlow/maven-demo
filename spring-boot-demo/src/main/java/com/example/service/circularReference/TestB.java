package com.example.service.circularReference;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Javen
 * @date 2022/2/14
 */
@Service
public class TestB {

    @Autowired
    private TestA testA;

//    private final TestA testA;
//
//    @Autowired
//    public TestB(@Lazy TestA testA) {
//        System.out.println("Test B ::::: Constructor");
//        this.testA = testA;
//    }

    public void print() {
        System.out.println(testA);
    }
}
