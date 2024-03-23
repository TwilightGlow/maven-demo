package com.example.ioc;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.beans.factory.support.SimpleInstantiationStrategy;

/**
 * @author zhangjw54
 */
@Slf4j
public class InstantiationTest {

    @Test
    public void testInstantiation() throws ClassNotFoundException {
        // 编写BeanDefinition
        RootBeanDefinition bd = new RootBeanDefinition();
        bd.setBeanClassName("com.example.bean.Parent");
        MutablePropertyValues propertyValues = new MutablePropertyValues();
        propertyValues.addPropertyValue("name", "Javen");
        propertyValues.addPropertyValue("age", 32);
        bd.setPropertyValues(propertyValues);
        bd.resolveBeanClass(this.getClass().getClassLoader());
        bd.resolveBeanClass(Thread.currentThread().getContextClassLoader());
        // 将BD注册到BeanFactory
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        beanFactory.registerBeanDefinition("user", bd);
        // 实例化
        SimpleInstantiationStrategy instantiationStrategy = new SimpleInstantiationStrategy();
        Object user = instantiationStrategy.instantiate(bd, "user", beanFactory);
        // 属性填充
        BeanWrapper wrapper = new BeanWrapperImpl(user);
        wrapper.setPropertyValues(bd.getPropertyValues());
        log.info("The user is [{}]", user);
    }
}
