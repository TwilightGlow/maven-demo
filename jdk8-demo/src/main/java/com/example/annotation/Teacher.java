package com.example.annotation;

import lombok.Data;

import java.util.List;

/**
 * @author Javen
 * @date 2022/4/18
 */
@Data
@TargetTypeUseAnnotation(value = "类")
public class Teacher<@TargetTypeUseAnnotation @TargetTypeParameterAnnotation T> {

    @TargetTypeUseAnnotation(value = "成员变量1")
    @TargetTypeUseAnnotation(value = "成员变量2")
    public String name;

    public List<T> hobbies;

    // @TargetTypeUseAnnotation(value = "方法上")
    public @TargetTypeUseAnnotation(value = "方法返回值") String teach(@TargetTypeUseAnnotation(value = "方法形参") int num) {
        System.out.println("This is teach method");
        return String.valueOf(num);
    }

    @TargetTypeUseAnnotation(value = "方法上")
    public @TargetTypeUseAnnotation(value = "方法返回值") String repeat(@TargetTypeUseAnnotation(value = "方法形参") int num) {
        System.out.println("This is teach method");
        return String.valueOf(num);
    }

    public String customizedPara(Teacher teacher) {
        return "123";
    }
}
