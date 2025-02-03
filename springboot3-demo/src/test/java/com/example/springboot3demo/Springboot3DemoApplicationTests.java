package com.example.springboot3demo;

import com.example.springboot3demo.bean.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Springboot3DemoApplicationTests {

    @Test
    void contextLoads() throws NoSuchMethodException {
        Book book = BeanUtils.instantiateClass(Book.class);
        System.out.println(book);

        Book book1 = BeanUtils.instantiateClass(Book.class.getDeclaredConstructor(String.class), "aaaaa");
        System.out.println(book1);
    }

}
