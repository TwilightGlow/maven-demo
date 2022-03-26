package com.example.load;

/**
 * @author Javen
 * @date 2022/3/6
 */
public class Teacher {

    static {
        System.out.println("静态代码块");
    }

    public static final Student finalStaticStudent = new Student("final静态变量");
    public static Student normalStaticStudent = new Student("静态变量");
    public static final String finalStaticStr = "123";
    public static String StaticStr = "123";

    public Student student = new Student("成员变量");

    public void output() {
        System.out.println(student);
    }

    public static void method() {
        new Student("静态方法");
    }

    public static class InnerStaticClass {
        public static final String STR_FINAL = "12345";
        public static String STR_NOT_FINAL = "12345";
        static {
            System.out.println("InnerStaticClass静态代码块执行");
        }
    }

    public class InnerClass {
        {
            System.out.println("InnerStaticClass普通代码块执行");
        }
    }
}
