package com.example.reflect;

/**
 * @author Javen
 * @date 2022/2/27
 */
public class TestReflect {

    public static void main(String[] args) throws InterruptedException {
        System.out.println(System.getProperty("aaa"));
        System.out.println(System.getenv("bbb"));
//        for (int i = 0; i < 1000000; i++) {
//            new Thread(() -> {
//                try {
//                    Class<Foo> fooClass = Foo.class;
//                    System.out.println(fooClass.getConstructor().newInstance());
//                    Thread.sleep(2000);
//                } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException | InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }).start();
//        }
//        Thread.currentThread().join();
    }
}
