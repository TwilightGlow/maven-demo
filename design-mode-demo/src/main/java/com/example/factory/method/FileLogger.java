package com.example.factory.method;

/**
 * @author Javen
 * @date 2022/2/2
 */
public class FileLogger implements Logger {

    public FileLogger() {
        System.out.println("Create a FileLogger with no arg");
    }

    public FileLogger(String str) {
        System.out.println("Create a FileLogger with String " + str);
    }

    @Override
    public void writeLog() {
        System.out.println("This is File Logger to write log");
    }
}
