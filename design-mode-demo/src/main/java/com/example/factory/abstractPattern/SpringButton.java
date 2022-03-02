package com.example.factory.abstractPattern;

/**
 * @author Javen
 * @date 2022/2/2
 */
public class SpringButton implements Button {

    @Override
    public void display() {
        System.out.println("显示浅绿色按钮。");
    }
}
