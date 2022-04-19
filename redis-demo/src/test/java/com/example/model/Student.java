package com.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author Javen
 * @date 2022/4/7
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student implements Serializable {

//    private static final long serialVersionUID = 3725680139212570337L;

    private String name;
    private Integer age;
    private Date time;
    private Short type;
    private Boolean kind;
    private List<String> hobby;
    private List<Friend> friends;
}
