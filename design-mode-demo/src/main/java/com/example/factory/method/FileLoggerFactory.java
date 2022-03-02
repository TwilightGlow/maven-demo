package com.example.factory.method;

/**
 * @author Javen
 * @date 2022/2/2
 */
public class FileLoggerFactory implements LoggerFactory {

    @Override
    public FileLogger createLogger() {
        return new FileLogger();
    }

    @Override
    public FileLogger createLogger(String str) {
        return new FileLogger(str);
    }
}
