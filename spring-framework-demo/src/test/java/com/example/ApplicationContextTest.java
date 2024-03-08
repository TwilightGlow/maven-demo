package com.example;

import com.example.controller.JavenController;
import com.example.service.Parent;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author Javen
 * @date 2022/3/3
 */
@Slf4j
public class ApplicationContextTest {

    @Test
    public void classPathXmlApplicationContext() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-parent.xml");
        Parent parent = (Parent) context.getBean("parent");
        log.info("Bean属性：{}", parent.getName());
    }

    @Test
    public void annotationConfigApplicationContext() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        log.info("Controller Bean :{}", context.getBean(JavenController.class).test());
        // 注册BeanDefinition
        BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(Parent.class);
        builder.addPropertyValue("name", "Javen");
        AbstractBeanDefinition beanDefinition = builder.getBeanDefinition();
        context.registerBeanDefinition("createBean", beanDefinition);
        Parent parent = context.getBean("createBean", Parent.class);
        log.info("通过BeanDefinition创建的Bean: {}, 属性：{}", parent, parent.getName());
        context.getBeansOfType(Parent.class).forEach((k, v) -> {
            log.info("Bean名称：{}, Bean实例：{}", k, v);
        });
    }
}
