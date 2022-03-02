package com.example.factory.simple;

/**
 * @author Javen
 * @date 2022/2/2
 */
public class Apple extends Computer {

    @Override
    protected void name() {
        System.out.println("I am Apple.");
    }
}

