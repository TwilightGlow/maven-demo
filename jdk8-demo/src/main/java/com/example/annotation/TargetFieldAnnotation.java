package com.example.annotation;

import java.lang.annotation.*;

/**
 * @author zhangjw54
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface TargetFieldAnnotation {

    String value() default "Kenny";
}
