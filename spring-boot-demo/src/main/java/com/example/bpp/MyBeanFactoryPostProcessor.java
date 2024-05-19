package com.example.bpp;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

/**
 * @author Javen
 * @date 2024/5/18
 */
@Slf4j
@Component
public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        // 这里的beanFactory是DefaultListableBeanFactory
        log.info("回调了接口BeanFactoryPostProcessor, 类型： {}, hashcode：{}", beanFactory.getClass().getName(), beanFactory.hashCode());
    }
}
