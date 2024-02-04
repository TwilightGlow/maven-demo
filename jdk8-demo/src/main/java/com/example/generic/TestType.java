package com.example.generic;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

/**
 * Type是所有类型的父接口，总共有五种类型
 * 1.原始类型（Class）
 * 2.参数化类型（ParameterizedType）
 * 3.数组类型（GenericArrayType）
 * 4.类型变量（TypeVariable）
 * 5.通配符类型（WildcardType）
 *
 * @author zhangjw54
 */
public class TestType<T> {
    public List clazz;
    public List<T> parameterizedType;
    public T[] genericArrayType;
    public T typeVariable;
    public List<? super String> wildcardType;

    // 获取属性所对应的Type
    @Test
    public void testType() {
        for (Field field : TestType.class.getFields()) {
            System.out.println(field.getName() + " : " + field.getGenericType().getClass());
            // 对于wildcardType类型，获取属性本身的类型也是ParameterizedType
            // 如果List<T>，那么getActualTypeArguments()获取到的就是TypeVariableImpl
            // 如果是List<? super String>，那么getActualTypeArguments()获取到的就是WildcardTypeImpl
            // 如果是List<String>，那么ActualTypeArguments()获取到的就是Class
            // 如果是List，那么属性本身就是Class而不是ParameterizedType
            if (field.getGenericType() instanceof ParameterizedType) {
                ParameterizedType parameterizedType = (ParameterizedType) field.getGenericType();
                for (Type actualTypeArgument : parameterizedType.getActualTypeArguments()) {
                    System.out.println(field.getName() + " : " + actualTypeArgument.getClass());
                    System.out.println(field.getName() + " : " + actualTypeArgument.getTypeName());
                }
            }
            System.out.println("--------------------------------------");
        }
    }

    // 获取Type的方式
    @Test
    public void getType() throws NoSuchFieldException {
        // Field类中的getType()返回字段所属的Class，getGenericType()返回字段所属的Type，印证了对于普通对象Type就是Class
        // getClass()是返回Field类本身所在类，所以jdk1.5用getType()表示字段实际所指向的类
        Field clazz = TestType.class.getDeclaredField("clazz");
        System.out.println(clazz.getClass());
        System.out.println(clazz.getType());
        System.out.println(clazz.getGenericType());
        System.out.println(clazz.getType() == clazz.getGenericType());

        Field parameterizedType = TestType.class.getDeclaredField("parameterizedType");
        System.out.println(parameterizedType.getClass());
        System.out.println(parameterizedType.getType());
        System.out.println(parameterizedType.getGenericType());
        System.out.println(parameterizedType.getType() == parameterizedType.getGenericType());
        System.out.println(parameterizedType.getAnnotatedType().getType() == parameterizedType.getGenericType());
    }

    private List<String> list;

    // 获取属性上泛型所对应的真实类型
    @Test
    public void getActualGeneric() throws NoSuchFieldException {
        Field list = TestType.class.getDeclaredField("list");
        Class<?> type = list.getType();
        Type genericType = list.getGenericType();
        if (genericType instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) genericType;
            for (Type actualTypeArgument : parameterizedType.getActualTypeArguments()) {
                System.out.println(actualTypeArgument.getTypeName());
            }
        }
    }


}
