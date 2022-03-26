package com.example.singleton;

/**
 * @author Javen
 * @date 2022/3/16
 */
public class HungrySingleton {

    // 饿汉式在类创建的同时就已经创建好一个静态的对象供系统使用，以后不再改变，所以天生是线程安全的。
    private HungrySingleton() {
    }

    private static final HungrySingleton singleton = new HungrySingleton();

    public static HungrySingleton getInstance() {
        return singleton;
    }
}
