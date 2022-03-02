package com.example.factory.simple;

/**
 * @author Javen
 * @date 2022/2/2
 */
public class Test {

    public static void main(String[] args) {
        ProductFactory.getComputer("Apple").name();
        ProductFactory.getComputer("Lenovo").name();
        ProductFactory.getComputer("Dell").name();
    }
}
