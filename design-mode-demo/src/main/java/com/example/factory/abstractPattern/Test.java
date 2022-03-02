package com.example.factory.abstractPattern;

/**
 * @author Javen
 * @date 2022/2/2
 */
public class Test {

    public static void main(String[] args) {
        new SpringSkinFactory().createButton().display();
        new SpringSkinFactory().createTextField().display();
        new SpringSkinFactory().createComboBox().display();

        new SummerSkinFactory().createButton().display();
        new SummerSkinFactory().createTextField().display();
        new SummerSkinFactory().createComboBox().display();
    }
}
