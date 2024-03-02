package com.example.facade;

/**
 * @author Javen
 * @date 2024/3/2
 */
public class Client {

    public static void main(String[] args) {

        // 创建一个外观对象
        Facade facade = new Facade();

        // 调用外观对象的方法，完成一系列操作
        facade.test();
    }
}
