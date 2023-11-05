package com.example.proxy.config;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;

import java.util.HashMap;
import java.util.Map;

/**
 * 模拟@Configuration实现@Bean单例执行
 *
 * @author zhangjw54
 */
public class Config {

    public static void main(String[] args) {
        Map<String, Object> beans = new HashMap<>();

        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(Config.class);
        enhancer.setCallback((MethodInterceptor) (obj, method, arguments, proxy) -> {
            String name = method.getName();
            System.out.println("Before: " + name);
            if (!beans.containsKey(name)) {
                Object bean = proxy.invokeSuper(obj, arguments);
                beans.put(name, bean);
            }
            return beans.get(name);
        });

        Config config = (Config) enhancer.create();
        System.out.println(config.getClass());

        config.foo().doSomething();
        config.foo().doSomething();
    }

    public Bar bar() {
        System.out.println("Config.bar");
        return new Bar();
    }

    public Foo foo() {
        System.out.println("Config.foo");
        return new Foo(bar());
    }

    public static class Foo {
        private final Bar bar;

        public Foo(Bar bar) {
            this.bar = bar;
        }

        public void doSomething() {
            System.out.println(this.toString() + "#" + bar.toString());
        }
    }

    public static class Bar {

    }
}
