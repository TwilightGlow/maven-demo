package com.example.bpp;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.stereotype.Component;

/**
 * @author Javen
 * @date 2024/5/18
 */
@Slf4j
@Component
public class MyBeanDefinitionRegistryPostProcessor implements BeanDefinitionRegistryPostProcessor {

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        // 这里的registry是DefaultListableBeanFactory
        // BeanDefinitionRegistryPostProcessor要早于BeanFactoryPostProcessor
        log.info("回调了接口BeanDefinitionRegistryPostProcessor, 类型： {}, hashcode：{}", registry.getClass().getName(), registry.hashCode());
    }
}
