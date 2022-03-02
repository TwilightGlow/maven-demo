package com.example.reflect;

/**
 * @author Javen
 * @date 2022/2/27
 */

public class Foo {

    private String name;
    private Integer age;

    public Foo() {
    }

    public Foo(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
