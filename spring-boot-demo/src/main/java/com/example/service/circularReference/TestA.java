package com.example.service.circularReference;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @author Javen
 * @date 2022/2/14
 */
@Service
public class TestA {

    @Autowired
    @Lazy
    private TestB testB;

//    private final TestB testB;
//
//    @Autowired
//    public TestA(TestB testB) {
//        System.out.println("Test A ::::: Constructor");
//        this.testB = testB;
//    }

    @Async
    public void print() {
        System.out.println(testB);
    }

}
