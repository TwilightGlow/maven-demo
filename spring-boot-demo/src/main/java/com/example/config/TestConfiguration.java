package com.example.config;

import com.example.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Async;

/**
 * @author Javen
 * @date 2022/2/12
 */
@Configuration
// 被 @Configuration修饰的类在初始化时，首先被ConfigurationClassEnhancer做CGLib增强
// 增强以后，配置类中使用@Bean注解的bean定义方法就不再是普通的方法了，具有了如下跟bean作用域有关的能力：
// 1. 它们首次被调用时，相应方法逻辑会被执行用于创建bean实例；
// 2. 再次被调用时，不会再执行创建bean实例，而是根据bean名称返回首次该方法被执行时创建的bean实例。
// 然后再判断是否需要AOP，如果需要则不论是类还是接口，都做CGLib增强
public class TestConfiguration implements DummyInterface {

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

    @Bean
    public Student student() {
        Student student = new Student();
        student.setName("Javen");
        student.setAge(28);
        System.out.println("创建了Student");
        return student;
    }

    @Bean
    public Student getStudent1() {
        return student();
    }

    @Bean
    public Student getStudent2() {
        return student();
    }
}




