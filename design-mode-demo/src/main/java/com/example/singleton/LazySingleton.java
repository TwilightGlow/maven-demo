package com.example.singleton;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author Javen
 * @date 2022/3/16
 */
public class LazySingleton implements Serializable {

    private LazySingleton() {
    }

    // volatile 有两个主要的作用，第一，解决内存可见性问题，第二，防止指令重排序。
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

    // 解决反序列化创建问题
    // 若类中定义一个readResolve方法，其返回值将会替代之前创建的新对象，一次保证了反序列化后仍然是原来的对象。
    // 但是与枚举实现相比，还是枚举实现具有更高的优先级
    @Serial
    private Object readResolve() {
        return getInstance();
    }
}
