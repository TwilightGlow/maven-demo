package com.example.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
 * @author Javen
 * @date 2022/4/18
 */
@Target(ElementType.TYPE_PARAMETER)
//@Retention(RetentionPolicy.RUNTIME)
public @interface TypeParaAnnotation {

    String value() default "Javen";
}
