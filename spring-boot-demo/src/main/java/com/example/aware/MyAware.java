package com.example.aware;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.BeanClassLoaderAware;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.context.*;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.metrics.ApplicationStartup;
import org.springframework.stereotype.Component;
import org.springframework.util.StringValueResolver;

/**
 * @author Javen
 * @date 2024/5/19
 */
@Slf4j
@Component
public class MyAware implements BeanNameAware, BeanClassLoaderAware, BeanFactoryAware,
        EnvironmentAware, EmbeddedValueResolverAware, ResourceLoaderAware, ApplicationEventPublisherAware, MessageSourceAware, ApplicationStartupAware, ApplicationContextAware {

    @Override
    public void setBeanName(String name) {
        log.info("回调了接口BeanNameAware {}", name);
    }

    @Override
    public void setBeanClassLoader(ClassLoader classLoader) {
        // AppClassLoader
        log.info("回调了接口BeanClassLoaderAware, 类型： {}, hashcode：{}", classLoader.getClass().getName(), classLoader.hashCode());
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) {
        // DefaultListableBeanFactory
        log.info("回调了接口BeanFactoryAware, 类型： {}, hashcode：{}", beanFactory.getClass().getName(), beanFactory.hashCode());
    }

    @Override
    public void setEnvironment(Environment environment) {
        // ApplicationServletEnvironment
        log.info("回调了接口EnvironmentAware, 类型： {}, hashcode：{}", environment.getClass().getName(), environment.hashCode());
    }

    @Override
    public void setEmbeddedValueResolver(StringValueResolver resolver) {
        // EmbeddedValueResolver
        log.info("回调了接口EmbeddedValueResolverAware, 类型： {}, hashcode：{}", resolver.getClass().getName(), resolver.hashCode());
    }

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        // AnnotationConfigServletWebServerApplicationContext
        log.info("回调了接口ResourceLoaderAware, 类型： {}, hashcode：{}", resourceLoader.getClass().getName(), resourceLoader.hashCode());
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        // AnnotationConfigServletWebServerApplicationContext
        log.info("回调了接口ApplicationEventPublisherAware, 类型： {}, hashcode：{}", applicationEventPublisher.getClass().getName(), applicationEventPublisher.hashCode());
    }

    @Override
    public void setMessageSource(MessageSource messageSource) {
        // AnnotationConfigServletWebServerApplicationContext
        log.info("回调了接口MessageSourceAware, 类型： {}, hashcode：{}", messageSource.getClass().getName(), messageSource.hashCode());
    }

    @Override
    public void setApplicationStartup(ApplicationStartup applicationStartup) {
        // DefaultApplicationStartup
        log.info("回调了接口ApplicationStartupAware, 类型： {}, hashcode：{}", applicationStartup.getClass().getName(), applicationStartup.hashCode());
    }


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        // AnnotationConfigServletWebServerApplicationContext
        log.info("回调了接口ApplicationContextAware, 类型： {}, hashcode：{}", applicationContext.getClass().getName(), applicationContext.hashCode());
    }
}
