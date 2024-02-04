package com.example.annotation;

/**
 * @author zhangjw54
 */
public class Bar<@TargetTypeParameterAnnotation("Gallen") T> {

    public String name;

    public T context;
}
