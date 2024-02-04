package com.example.annotation;

/**
 * @author zhangjw54
 */
public class Foo {
    @TargetTypeUseAnnotation("Javen")
    public String nameWithTypeUseAnnotation;

    @TargetFieldAnnotation("Sophia")
    private String nameWithFieldAnnotation;
}
