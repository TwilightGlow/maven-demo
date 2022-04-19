package com.example.annotation;

import org.junit.jupiter.api.Test;

/**
 * @author Javen
 * @date 2022/4/18
 */
// TYPE_USE用于任意类型
// 通过反射拿到ElementType.TYPE_USE修饰的注解可以用get…Type()方法获取
// The TYPE_USE constant includes type declarations and type parameter declarations
// as a convenience for designers of type checkers which give semantics to annotation types.
public class AnnotationTypeUseTest {

    @Test
    // 类上的注解
    public void testClass() {
        TestAnnotation annotation = (TestAnnotation) Teacher.class.getAnnotations()[0];
        System.out.println("类上的注解：" + annotation.value());
    }

    @Test
    // TYPE_USE不可以直接获得类上的注解
    public void testMethod() throws NoSuchMethodException {
        TestAnnotation annotation = (TestAnnotation) Teacher.class.getMethod("teach", int.class).getAnnotations()[0];
        System.out.println("类上的注解：" + annotation.value());
    }

    @Test
    // 方法形参上的注解
    public void testParameterTypes() throws NoSuchMethodException {
        TestAnnotation annotation = (TestAnnotation) Teacher.class.getMethod("teach", int.class)
                .getAnnotatedParameterTypes()[0]
                .getAnnotations()[0];
        System.out.println("方法形参上的注解：" + annotation.value());
    }

    @Test
    // 方法返回值上的注解
    public void testReturnType() throws NoSuchMethodException {
        TestAnnotation annotation = (TestAnnotation) Teacher.class.getMethod("teach", int.class)
                .getAnnotatedReturnType()
                .getAnnotations()[0];
        System.out.println("方法返回值上的注解：" + annotation.value());
    }

    @Test
    // 成员变量上的复合注解
    public void testField() {
        TestAnnotations testAnnotations = (TestAnnotations) Teacher.class.getFields()[0]
                .getAnnotatedType()
                .getAnnotations()[0];
        TestAnnotation[] annotations = testAnnotations.value();
        for (TestAnnotation annotation : annotations) {
            System.out.println("成员变量上的注解: " + annotation.value());
        }

//        TestAnnotation annotation1 = (TestAnnotation) Teacher.class.getFields()[0]
//                .getAnnotatedType()
//                .getAnnotations()[0];
//        System.out.println("成员变量上的注解：" + annotation1.value());

//        TestAnnotation annotation2 = (TestAnnotation) Teacher.class.getFields()[0]
//                .getAnnotatedType()
//                .getAnnotations()[1];
//        System.out.println("成员变量上的注解：" + annotation2.value());
    }
}
