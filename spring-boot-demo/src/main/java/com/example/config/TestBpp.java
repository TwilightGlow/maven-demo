package com.example.config;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * @author Javen
 * @date 2022/2/12
 */
@Component
public class TestBpp implements BeanPostProcessor {

    public TestBpp() {
        System.out.println("1111");
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        // @Configuration com.example.config.AppConfig$$EnhancerBySpringCGLIB$$51d80606@23f72d88
        // @Component com.example.config.AppConfig@3fbfbf84
        if (beanName.equals("testComponent") || beanName.equals("testConfiguration")) {
            for (Class<?> aClass : bean.getClass().getInterfaces()) {
                System.out.println(aClass.getName());
            }
            System.out.println(bean.getClass() + ":::" + beanName);
        }
        return BeanPostProcessor.super.postProcessAfterInitialization(bean, beanName);
    }

}
