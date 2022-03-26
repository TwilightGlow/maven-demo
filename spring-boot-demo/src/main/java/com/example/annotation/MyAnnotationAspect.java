package com.example.annotation;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @author Javen
 * @date 2022/3/12
 */
@Slf4j
@Aspect
@Component
public class MyAnnotationAspect {

    @Pointcut("@annotation(com.example.annotation.MyAnnotation)")
    public void pointcut() {}

    @Around("pointcut()")
    public Object doAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Signature signature = proceedingJoinPoint.getSignature();
        Method method = signature.getDeclaringType().getMethods()[0];
        MyAnnotation annotation = method.getAnnotation(MyAnnotation.class);
        System.out.println(annotation.value());
        System.out.println("12345");
        Object proceed = proceedingJoinPoint.proceed();
        return proceed;
    }
}
