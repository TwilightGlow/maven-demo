package com.example.singleton;

import java.io.*;
import java.lang.reflect.Constructor;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @author Javen
 * @date 2022/3/16
 */
public class TestSingleton {

    public static void main(String[] args) throws Exception {

        // 静态内部类看起来很完美了，但是仍然可能被反射或者反序列化攻击
        // 测试反射
        StaticInnerClassSingleton instance = StaticInnerClassSingleton.getInstance();
        Constructor<StaticInnerClassSingleton> constructor = StaticInnerClassSingleton.class.getDeclaredConstructor();
        constructor.setAccessible(true);
        StaticInnerClassSingleton singleton = constructor.newInstance();
        System.out.println(instance == singleton);

        EnumSingleton instance1 = EnumSingleton.getInstance();
        Constructor<EnumSingleton> constructor1 = EnumSingleton.class.getDeclaredConstructor();
        constructor1.setAccessible(true);
        EnumSingleton singleton1 = constructor1.newInstance();
        System.out.println(instance1 == singleton1);

//        // 枚举类不允许反射创建
//        EnumSingleton1 instance2 = EnumSingleton1.SINGLETON;
//        Constructor<EnumSingleton1> constructor2 = EnumSingleton1.class.getDeclaredConstructor();
//        constructor2.setAccessible(true);
//        EnumSingleton1 singleton2 = constructor2.newInstance();
//        System.out.println(instance2 == singleton2);

        // 测试反序列化
        EnumSingleton1 singleton10 = EnumSingleton1.SINGLETON;
        ObjectOutputStream oos = new ObjectOutputStream(Files.newOutputStream(Paths.get("test")));
        oos.writeObject(EnumSingleton1.SINGLETON);
        oos.close();
        File file = new File("test");
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
        EnumSingleton1 singleton11 = (EnumSingleton1) ois.readObject();
        ois.close();
        boolean delete = file.delete();
        System.out.println(singleton10 == singleton11);


        ObjectOutputStream oos1 = new ObjectOutputStream(Files.newOutputStream(Paths.get("test1")));
        oos1.writeObject(LazySingleton.getInstance());
        oos1.close();
        File file1 = new File("test1");
        ObjectInputStream ois1 = new ObjectInputStream(new FileInputStream(file1));
        LazySingleton lazySingleton = (LazySingleton) ois1.readObject();
        ois1.close();
        boolean delete1 = file1.delete();
        System.out.println(LazySingleton.getInstance() == lazySingleton);


    }
}
