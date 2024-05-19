package com.example.annotation;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
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

    // 当一个类中定义了多个切面，按照方法首字母的优先级顺序执行
    @Around("pointcut()")
    public Object doAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Method method = ((MethodSignature) proceedingJoinPoint.getSignature()).getMethod();
        MyAnnotation annotation = method.getAnnotation(MyAnnotation.class);
        System.out.println("切面一执行，" + "名字：" + annotation.value() + "，颜色：" + annotation.fruitColor());
        return proceedingJoinPoint.proceed();
    }

    // 这种方式可以在方法参数中直接获取到注解
    @Around("@annotation(myAnnotation)")
    public Object around(ProceedingJoinPoint proceedingJoinPoint, MyAnnotation myAnnotation) throws Throwable {
        System.out.println("切面二执行，" + "名字：" + myAnnotation.value() + "，颜色：" + myAnnotation.fruitColor());
        return proceedingJoinPoint.proceed();
    }
}
