package com.example.factory.method;

/**
 * @author Javen
 * @date 2022/2/2
 */
public class DatabaseLogger implements Logger {

    public DatabaseLogger() {
        System.out.println("Create a DatabaseLogger with no arg");
    }

    public DatabaseLogger(String str) {
        System.out.println("Create a DatabaseLogger with String " + str);
    }

    @Override
    public void writeLog() {
        System.out.println("This is Database Logger to write log");
    }
}
