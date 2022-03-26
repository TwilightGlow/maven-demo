package com.example.load;

import org.junit.Test;

import java.lang.reflect.InvocationTargetException;

/**
 * @author Javen
 * @date 2022/3/6
 */
public class TestLoadProcess {

    static {
        System.out.println("TestLoadProcess的静态代码块");
    }

    // 静态代码块和静态变量在类加载时最先执行，执行顺序是代码声明顺序
    // 普通代码块和普通变量在new对象时执行，执行顺序是代码声明顺序，每次创建对象都会执行一次
    // 如果只调用类的静态方法，那么静态代码块会执行普通代码块不会执行（除非在静态块中显示调用）
    public static void main(String[] args) {
//        Teacher teacher1 = new Teacher();
//        Teacher teacher2 = new Teacher();
//        Teacher teacher3 = new Teacher();
//        teacher1.output();
//        teacher2.output();
//        teacher3.output();
//        Teacher.method();
//        new Thread(() -> {
//            Teacher teacher = new Teacher();
//            teacher.output();
//        }).start();
//        System.out.println(Teacher.finalStaticStr);
        System.out.println(Teacher.StaticStr);
    }

    @Test
    public void testFinalStatic() {
        // 如果静态变量声明成final类型，jvm会做优化此时不会加载其他static变量
        System.out.println(Teacher.InnerStaticClass.STR_FINAL);
        // 如果是普通静态变量，那调用时会加载所有静态代码块
//        System.out.println(Teacher.InnerStaticClass.STR_NOT_FINAL);
    }

    @Test
    public void testClassLoader() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Class<?> aClass = TestLoadProcess.class.getClassLoader().loadClass("com.example.load.Teacher");
        Class<?> bClass = TestLoadProcess.class.getClassLoader().loadClass("com.example.load.Teacher");
        Class<?> cClass = Student.class.getClassLoader().loadClass("com.example.load.Teacher");
        System.out.println(aClass);
        System.out.println(bClass);
        System.out.println(cClass);

        System.out.println(aClass.getClassLoader());
        System.out.println(bClass.getClassLoader());
        System.out.println(cClass.getClassLoader());

        // ExtClassLoader
        System.out.println(aClass.getClassLoader().getParent());
        // BootStrapClassLoader
        System.out.println(aClass.getClassLoader().getParent().getParent());

//        Teacher teacher1 = Teacher.class.getConstructor().newInstance();
//        Teacher teacher2 = Teacher.class.getConstructor().newInstance();
//        System.out.println(teacher1);
//        System.out.println(teacher2);
    }
}
