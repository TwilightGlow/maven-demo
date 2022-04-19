package com.example.interceptor;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Javen
 * @date 2022/3/31
 */
@Configuration
public class TestMethodInterceptorConfig {

//    private static final String POINT_CUT = "execution(* com.example.controller..*.*(..))";
    private static final String POINT_CUT = "execution(* com.example.controller.MyController.trans(..))";

    @Bean
    public DefaultPointcutAdvisor defaultPointcutAdvisor() {
        TestMethodInterceptor interceptor = new TestMethodInterceptor();
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression(POINT_CUT);
        DefaultPointcutAdvisor defaultPointcutAdvisor = new DefaultPointcutAdvisor();
        defaultPointcutAdvisor.setPointcut(pointcut);
        defaultPointcutAdvisor.setAdvice(interceptor);
        return defaultPointcutAdvisor;
    }

    static class TestMethodInterceptor implements MethodInterceptor {
        @Override
        public Object invoke(MethodInvocation invocation) throws Throwable {
            System.out.println("This is customized MethodInterceptor...");
            return invocation.proceed();
        }
    }
}
