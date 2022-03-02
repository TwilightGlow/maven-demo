package com.example.test.proxyFactory;

import com.example.model.Student;
import org.junit.jupiter.api.Test;
import org.springframework.aop.framework.ProxyFactory;

/**
 * @author Javen
 * @date 2022/2/14
 */
//@SpringBootTest
public class TestProxy {

    @Test
    public void test() {
        // 无接口为CGLib代理，对于不指定接口的使用CGLIB的方式
        // 对于指定接口的代理类，Spring中使用的是JDK的Proxy
        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.setTarget(new Student("Javen", 28));
        proxyFactory.addAdvice(new TestInterceptor());
        Student proxy = (Student) proxyFactory.getProxy();
        System.out.println(proxy.getName());
        System.out.println(proxy.getClass().getName());
    }
}
