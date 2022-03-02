package com.example.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @author Javen
 * @date 2022/2/12
 */
@Component
// 被 @Component修饰的类在初始化时，不会做增强
// 然后再判断是否需要AOP，如果是接口做jdk增强，如果是类做CGlib增强
public class TestComponent implements DummyInterface {

    @Autowired
    private ApplicationContext applicationContext;

    @Async
    public void test() {
        System.out.println(applicationContext.getParent());
    }

    @Override
    @Async
    public void testAsync() {
        System.out.println(applicationContext.getParent());
    }

//    @Bean
//    public Student student() {
//        Student student = new Student();
//        student.setName("Javen");
//        student.setAge(28);
//        System.out.println("创建了Student");
//        return student;
//    }
//
//    @Bean
//    public Student getStudent1() {
//        return student();
//    }
//
//    @Bean
//    public Student getStudent2() {
//        return student();
//    }
}
