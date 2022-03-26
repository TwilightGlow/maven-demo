package com.example.stream;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Javen
 * @date 2022/3/16
 */
@Data
public class Teacher implements Serializable {
    private static final long serialVersionUID = -2356043581004214957L;
    private String name;
    private Integer age;
}
