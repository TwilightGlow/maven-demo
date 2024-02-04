package com.example.annotation;

import org.junit.jupiter.api.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

/**
 * @author Javen
 * @date 2022/4/18
 */
// TYPE_USE用于Type类型，由于Type是所有类型的父接口，因此TYPE_USE可用于所有类型，一般从AnnotatedType对象上的方法获取注解
// 通过反射拿到ElementType.TYPE_USE修饰的注解可以用 get…Type()方法获取
// The TYPE_USE constant includes type declarations and type parameter declarations
// as a convenience for designers of type checkers which give semantics to annotation types.
public class TestAnnotationTypeUse {

    // 用在类上，用于Class本身也是Type，因此TYPE_USE也可以作用在类上直接就能获取到
    @Test
    public void testClass() {
        TargetTypeUseAnnotation annotation = (TargetTypeUseAnnotation) Teacher.class.getAnnotations()[0];
        System.out.println("类上的注解：" + annotation.value());
    }

    // TYPE_USE不可以直接获得类上的注解(方法上或者方法返回值上仅一个注解)，这是因为为了防止歧义，不能同时表示方法和方法返回值，此时TYPE_USE代表的方法返回值
    @Test
    public void testMethod() throws NoSuchMethodException {
        System.out.println(Teacher.class.getMethod("teach", int.class).getAnnotations().length);
        System.out.println(Teacher.class.getMethod("teach", int.class).getAnnotatedReturnType().getAnnotations().length);
    }

    // 用在方法形参上
    @Test
    public void testParameterTypes() throws NoSuchMethodException {
        TargetTypeUseAnnotation annotation = (TargetTypeUseAnnotation) Teacher.class.getMethod("teach", int.class)
                .getAnnotatedParameterTypes()[0]
                .getAnnotations()[0];
        System.out.println("方法形参上的注解：" + annotation.value());
    }

    // 用在方法返回值上, 注意如果在方法返回值上和方法上同时写了注解，getAnnotatedReturnType().getAnnotations()方法会失效
    @Test
    public void testReturnType() throws NoSuchMethodException {
        TargetTypeUseAnnotation annotation = (TargetTypeUseAnnotation) Teacher.class.getMethod("teach", int.class)
                .getAnnotatedReturnType()
                .getAnnotations()[0];
        System.out.println("方法返回值上的注解：" + annotation.value());
    }

    // 使用复合注解解析成员变量上的注解
    @Test
    @SuppressWarnings("all")
    public void testRepeatableAnnotation() throws NoSuchFieldException {
        Field field = Teacher.class.getField("name");
        Annotation[] annotationArray = field.getAnnotatedType().getAnnotations();
        System.out.println("成员变量上注解数组的长度：" + annotationArray.length);
        for (Annotation annotation : annotationArray) {
            System.out.println("注解的类型：" + annotation.getClass());
            System.out.println(annotation instanceof TargetTypeUseAnnotation);
            System.out.println(annotation instanceof TargetTypeUseAnnotations);
            for (TargetTypeUseAnnotation targetTypeUseAnnotation : ((TargetTypeUseAnnotations) annotation).value()) {
                System.out.println("注解的值：" + targetTypeUseAnnotation.value());
            }
        }

        TargetTypeUseAnnotations targetTypeUseAnnotations = (TargetTypeUseAnnotations) Teacher.class.getFields()[0]
                .getAnnotatedType()
                .getAnnotations()[0];
        TargetTypeUseAnnotation[] annotations = targetTypeUseAnnotations.value();
        for (TargetTypeUseAnnotation annotation : annotations) {
            System.out.println("成员变量上的注解: " + annotation.value());
        }
    }

    @Test
    public void testAnnotatedType() throws NoSuchFieldException {
        Field field = Foo.class.getDeclaredField("nameWithTypeUseAnnotation");
        System.out.println(field.getAnnotations().length);
        System.out.println(field.getAnnotatedType().getAnnotations().length);

        Field field2 = Foo.class.getDeclaredField("nameWithFieldAnnotation");
        System.out.println(field2.getAnnotations().length);
        System.out.println(field2.getAnnotatedType().getAnnotations().length);
    }
}
