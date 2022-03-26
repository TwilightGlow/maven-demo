package com.example;

import com.example.controller.JavenController;
import com.example.service.Parent;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotatedBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author Javen
 * @date 2022/3/3
 */
public class MyTest {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-parent.xml");
        Parent parent = (Parent) applicationContext.getBean("parent");
        System.out.println(parent.getName());

        AnnotationConfigApplicationContext applicationContext1 = new AnnotationConfigApplicationContext(AppConfig.class);
        JavenController javenController = (JavenController) applicationContext1.getBean("javenController");
        System.out.println(javenController.test());
    }

    @Test
    public void test1r() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        AnnotatedBeanDefinitionReader annotatedBeanDefinitionReader = new AnnotatedBeanDefinitionReader(context);
        annotatedBeanDefinitionReader.register(Parent.class);
        System.out.println(System.getenv());
        System.out.println(System.getProperties());
        System.out.println(context.getBean("parent"));
    }
}
