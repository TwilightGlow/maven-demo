package com.example.singleton;

/**
 * @author Javen
 * @date 2022/3/16
 */
public class StaticInnerClassSingleton {

    private StaticInnerClassSingleton() {
    }

    // 外部类加载时并不需要立即加载内部类，内部类不被加载则不去初始化singleton，故而不占内存
    // 第一次调用getInstance()方法会导致jvm加载SingletonHolder类
    // 这种方法不仅能保证线程安全，也能保证单例的唯一性，同时也延迟了单例的实例化
    // 静态内部类也有一个缺点，就是传参的问题。由于是内部类的形式去创建单例的，故而无法传递参数进去。
    private static class SingletonHolder {
        private static final StaticInnerClassSingleton singleton = new StaticInnerClassSingleton();
    }

    public static StaticInnerClassSingleton getInstance() {
        return SingletonHolder.singleton;
    }
}
