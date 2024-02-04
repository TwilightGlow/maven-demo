package com.example.annotation;

import java.lang.annotation.*;

/**
 * @author Javen
 * @date 2022/4/18
 */
@Target(ElementType.TYPE_USE)
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(TargetTypeUseAnnotations.class)
@Documented
public @interface TargetTypeUseAnnotation {

    String value() default "Kenny";
}
