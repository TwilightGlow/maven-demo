package com.example.annotation;

import org.junit.jupiter.api.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Javen
 * @date 2022/4/18
 */
// TYPE_PARAMETER一般是用于自定义类的泛型，不能用于声明方法，参数
public class TestAnnotationTypeParameter {

    // 通过子类获取泛型的具体类型
    @Test
    public void testChildClassObtainGeneric() {
        ParameterizedType genericSuperclass = (ParameterizedType) Student.class.getGenericSuperclass();
        for (Type actualTypeArgument : genericSuperclass.getActualTypeArguments()) {
            System.out.println(actualTypeArgument.getTypeName());
        }

        List<String> list = new ArrayList<>();
        Type type = list.getClass().getGenericSuperclass();
        if (type instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) type;
            for (Type actualTypeArgument : parameterizedType.getActualTypeArguments()) {
                System.out.println(actualTypeArgument.getTypeName());
            }
        }
    }

    @Test
    public void testTypeParameter() {
        // 通过Class获取注解上的属性
        System.out.println("Bar的TypeVariable长度：" + Bar.class.getTypeParameters().length);
        System.out.println("BarChild的TypeVariable长度：" + BarChild.class.getTypeParameters().length);
        for (TypeVariable<Class<Bar>> typeParameter : Bar.class.getTypeParameters()) {
            TargetTypeParameterAnnotation annotation = typeParameter.getAnnotation(TargetTypeParameterAnnotation.class);
            System.out.println(annotation.value());
        }

        // 通过Field获取注解上的属性
        Class<?> barChildClass = BarChild.class;
        Field[] fields = barChildClass.getFields();
        for (Field field : fields) {
            Type genericType = field.getGenericType();
            if (genericType instanceof TypeVariable) {
                TypeVariable<?> typeVariable = (TypeVariable<?>) genericType;
                TargetTypeParameterAnnotation annotation = typeVariable.getAnnotation(TargetTypeParameterAnnotation.class);
                System.out.println(annotation.value());
            }

            AnnotatedType annotatedType = field.getAnnotatedType();
            Annotation[] annotations = annotatedType.getAnnotations();
            Arrays.stream(annotations)
                    .filter(annotation -> annotation instanceof TargetTypeParameterAnnotation)
                    .map(annotation -> (TargetTypeParameterAnnotation) annotation)
                    .forEach(annotation -> {
                        String value = annotation.value();
                        System.out.println("Annotation Value: " + value);
                    });
        }

        // Type genericSuperclass = BarChild.class.getGenericSuperclass();
        // ParameterizedType parameterizedType = (ParameterizedType) genericSuperclass;
        // for (Type actualTypeArgument : parameterizedType.getActualTypeArguments()) {
        //     System.out.println(actualTypeArgument.getTypeName());
        // }
        // System.out.println(parameterizedType.getRawType().getTypeName());
    }
}
