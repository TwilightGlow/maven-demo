package com.example.test;

import com.example.service.circularReference.TestA;
import com.example.service.circularReference.TestB;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author Javen
 * @date 2022/2/12
 */
@SpringBootTest
public class MyTest {

    @Autowired
    private TestA testA;

    @Autowired
    private TestB testB;

    @Test
    public void test() {
        testA.print();
        testB.print();
    }


}
