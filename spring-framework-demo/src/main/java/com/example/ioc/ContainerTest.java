package com.example.ioc;

import com.example.bean.Parent;
import com.example.bean.Student;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

/**
 * @author zhangjw54
 */
@Slf4j
public class ContainerTest {

    @Test
    public void classPathXmlApplicationContext() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-beans.xml");
        Parent parent = (Parent) context.getBean("parent");
        log.info("Bean属性：{}", parent);
    }

    @Test
    public void genericApplicationContext() {
        GenericApplicationContext context = new GenericApplicationContext();
        new XmlBeanDefinitionReader(context).loadBeanDefinitions("spring-beans.xml");
        context.refresh();
        Parent parent = (Parent) context.getBean("parent");
        log.info("Bean属性：{}", parent);
    }

    @Test
    public void multipleClassPathXmlApplicationContext() {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-beans.xml", "spring-child-beans.xml");
        Parent parent = (Parent) context.getBean("parent");
        log.info("Parent Bean属性：{}", parent);
        Student student = (Student) context.getBean("student");
        log.info("Student Bean属性：{}", student);
        Student student1 = (Student) context.getBean("student1");
        log.info("Student1 Bean属性：{}", student1);
        Student student2 = (Student) context.getBean("student2");
        log.info("Student2 Bean属性：{}", student2);
    }
}
