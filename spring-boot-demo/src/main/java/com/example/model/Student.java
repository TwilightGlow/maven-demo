package com.example.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * @author Javen
 * @date 2022/2/12
 */
@NoArgsConstructor
@AllArgsConstructor
public class Student {

    private String name;
    private Integer age;

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
