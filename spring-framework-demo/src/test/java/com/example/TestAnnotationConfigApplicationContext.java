package com.example;

import com.example.service.Parent;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author Javen
 * @date 2022/3/5
 */
public class TestAnnotationConfigApplicationContext {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(Parent.class);
        builder.addPropertyValue("name", "Javen");
        AbstractBeanDefinition beanDefinition = builder.getBeanDefinition();
//        beanDefinition.setBeanClass(Parent.class);
//        beanDefinition.setAttribute("name", "Javen");
        context.registerBeanDefinition("parent111", beanDefinition);
        Parent parent = context.getBean("parent111", Parent.class);
        System.out.println(parent);
        System.out.println(parent.getName());
    }
}
