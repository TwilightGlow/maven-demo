package com.example.factory.method;

/**
 * @author Javen
 * @date 2022/2/2
 */
// 在以下情况下可以考虑使用工厂方法模式：
// (1) 客户端不知道它所需要的对象的类。在工厂方法模式中，客户端不需要知道具体产品类的类名，只需要知道所对应的工厂即可，
// 具体的产品对象由具体工厂类创建，可将具体工厂类的类名存储在配置文件或数据库中。
//
// (2) 抽象工厂类通过其子类来指定创建哪个对象。在工厂方法模式中，对于抽象工厂类只需要提供一个创建产品的接口，
// 而由其子类来确定具体要创建的对象，利用面向对象的多态性和里氏代换原则，在程序运行时，子类对象将覆盖父类对象，从而使得系统更容易扩展。
//
// 所以简单工厂模式是静态的，工厂方法模式是动态的（由工厂子类实现初始化逻辑）
public class Test {

    public static void main(String[] args) {
        LoggerFactory databaseLoggerFactory = new DatabaseLoggerFactory();
        LoggerFactory fileLoggerFactory = new FileLoggerFactory();

        databaseLoggerFactory.createLogger().writeLog();
        fileLoggerFactory.createLogger().writeLog();

        databaseLoggerFactory.createLogger("123").writeLog();
        fileLoggerFactory.createLogger("123").writeLog();

        // 可以对客户隐藏工厂方法
        databaseLoggerFactory.writeLog();
    }
}
