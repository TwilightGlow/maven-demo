package com.example.annotation;

import org.junit.jupiter.api.Test;

import java.lang.reflect.ParameterizedType;

/**
 * @author Javen
 * @date 2022/4/18
 */
// TYPE_PARAMETER一般是用于自定义类的泛型，不能用于声明方法，参数
public class AnnotationTypeParameterTest {

    @Test
    public void test() {
        ParameterizedType genericSuperclass = (ParameterizedType) Student.class.getGenericSuperclass();
        System.out.println(genericSuperclass);

//        Teacher<String> teacher = new Teacher<>();
//        System.out.println(teacher.getClass().getAnnotations());
//        TypeParaAnnotation[] annotationsByType = teacher.getClass().getAnnotationsByType(TypeParaAnnotation.class);
//        System.out.println(annotationsByType[0].value());

    }
}
