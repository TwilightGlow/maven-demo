package com.example.test.proxyFactory;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * @author Javen
 * @date 2022/2/14
 */
public class TestInterceptor implements MethodInterceptor {

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        System.out.println("动态代理调用前");
        Object proceed = invocation.proceed();
        System.out.println("动态代理调用后");
        return proceed;
    }
}
