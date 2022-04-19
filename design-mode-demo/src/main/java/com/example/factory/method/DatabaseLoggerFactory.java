package com.example.factory.method;

/**
 * @author Javen
 * @date 2022/2/2
 */
public class DatabaseLoggerFactory implements LoggerFactory {

    @Override
    public DatabaseLogger createLogger() {
        return new DatabaseLogger();
    }

    @Override
    public DatabaseLogger createLogger(String str) {
        return new DatabaseLogger(str);
    }
}
