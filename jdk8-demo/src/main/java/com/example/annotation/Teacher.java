package com.example.annotation;

import lombok.Data;

import java.util.List;

/**
 * @author Javen
 * @date 2022/4/18
 */
@Data
@TestAnnotation(value = "类")
public class Teacher<@TestAnnotation @TypeParaAnnotation T> {

    @TestAnnotation(value = "成员变量1")
    @TestAnnotation(value = "成员变量2")
    public String name;

    public List<T> hobbies;

//    @TestAnnotation(value = "Gallen")
    public @TestAnnotation(value = "方法返回值") String teach(@TestAnnotation(value = "方法形参") int num) {
        System.out.println("This is teach method");
        return String.valueOf(num);
    }

    public String customizedPara(Teacher teacher) {
        return "123";
    }
}
