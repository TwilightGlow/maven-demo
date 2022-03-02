package com.example.factory.abstractPattern;

/**
 * @author Javen
 * @date 2022/2/2
 */
public class SpringTextField implements TextField {
    @Override
    public void display() {
        System.out.println("显示绿色边框文本框。");
    }
}
