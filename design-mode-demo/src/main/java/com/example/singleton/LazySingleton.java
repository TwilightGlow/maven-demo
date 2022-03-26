package com.example.singleton;

/**
 * @author Javen
 * @date 2022/3/16
 */
public class LazySingleton {

    private LazySingleton() {
    }

    // 在 new LazySingleton()前后加入屏障，保证原子性
    private static volatile LazySingleton lazySingleton;

    public static LazySingleton getInstance() {
        if (null == lazySingleton) {
            synchronized (LazySingleton.class) {
                if (null == lazySingleton) {
                    lazySingleton = new LazySingleton();
                }
            }
        }
        return lazySingleton;
    }
}
