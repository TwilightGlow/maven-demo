package com.example.aop;

import cn.hutool.aop.aspects.TimeIntervalAspect;
import cn.hutool.aop.proxy.JdkProxyFactory;
import cn.hutool.aop.proxy.ProxyFactory;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ServiceLoaderUtil;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author zhangjw54
 */
public class HutoolProxy {

    @Test
    public void proxy() {
        Animal cat = new Cat();
        Animal proxy = new JdkProxyFactory().proxy(cat, TimeIntervalAspect.class);
        proxy.eat();
    }

    @Test
    public void serviceLoader() {
        ProxyFactory proxyFactory = ServiceLoaderUtil.loadFirstAvailable(ProxyFactory.class);
        System.out.println(proxyFactory);
        // ProxyFactory proxyFactory1 = ServiceLoaderUtil.loadFirst(ProxyFactory.class);
        // System.out.println(proxyFactory1);

        // ServiceLoader<ProxyFactory> proxyFactories = ServiceLoaderUtil.load(ProxyFactory.class);
        // System.out.println(proxyFactories.stream().count());
    }

    @Test
    public void date() {
        DateTime dateTime = DateUtil.parse("2021-07-01T00:10:00");
        System.out.println(dateTime);
        Date jdkDate = dateTime.toJdkDate();
        System.out.println(jdkDate);
        LocalDateTime localDateTime = dateTime.toLocalDateTime();
        System.out.println(localDateTime);
    }
}
