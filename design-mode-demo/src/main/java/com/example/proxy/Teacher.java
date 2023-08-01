package com.example.proxy;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Javen
 * @date 2022/3/12
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Teacher implements TestInterface {

    private String name;

    public String teach() {
        System.out.println("Teacher");
        return "This is teach method";
    }

    private String test() {
        System.out.println("Test");
        return "This is a private method";
    }

}
