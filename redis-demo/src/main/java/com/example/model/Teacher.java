package com.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author Javen
 * @date 2022/4/8
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Teacher implements Serializable {

    private String name;
    private Integer age;
}
