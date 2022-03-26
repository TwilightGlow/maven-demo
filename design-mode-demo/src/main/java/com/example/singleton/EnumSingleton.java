package com.example.singleton;

/**
 * @author Javen
 * @date 2022/3/16
 */
public class EnumSingleton {

    // 单元素的枚举类型是实现Singleton的最佳方案
    private EnumSingleton() {
    }

    public static EnumSingleton getInstance() {
        return Singleton.INSTANCE.getInstance();
    }

    private enum Singleton {
        INSTANCE;

        private final EnumSingleton singleton;

        Singleton() {
            this.singleton = new EnumSingleton();
        }

        private EnumSingleton getInstance() {
            return singleton;
        }
    }
}
