package com.example.bean;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author zhangjw54
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Student extends Parent {

    private Integer grade;
}
