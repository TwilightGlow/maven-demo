package com.example.annotation;

import java.lang.annotation.*;

/**
 * @author Javen
 * @date 2022/3/12
 */
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface MyAnnotation {

    String value() default "Javen";

    enum Color {BLUE, RED, GREEN}

    Color fruitColor() default Color.GREEN;
}