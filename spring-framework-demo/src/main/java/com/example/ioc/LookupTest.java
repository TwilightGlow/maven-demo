package com.example.ioc;

import com.example.AppConfig;
import com.example.bean.AbstractFactory;
import com.example.bean.Computer;
import com.example.bean.Phone;
import com.example.bean.Product;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author zhangjw54
 */
@Slf4j
public class LookupTest {

    // 以上我们并没有实现此createProduct()抽象方法但是运行结果依然可以生产手机或者电脑，
    // 这是由于Spring底层使用CGLIB代理动态生成了此抽象工厂的子类以及重写实现了其抽象方法。
    // 这里需要注意的是代理的对象不能是final修饰，其方法也不能是final修饰。
    // 否则Spring无法使用CGLIB代理动态生成子类方法创建对象。
    // 所以一般我们将被代理的类设置为抽象类，被代理类的方法设置为抽象方法，
    // 而且除此之外需要注意的是一般注入的对象的scope设置为多实例的，否则每次生成的都是同一对象。
    @Test
    public void lookupFactory() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        Product product = context.getBean(AbstractFactory.class).createProduct();
        log.info("The product is [{}]", product.hashCode());
        Product product1 = context.getBean(AbstractFactory.class).createProduct();
        log.info("The product1 is [{}]", product1.hashCode());
    }

    @Test
    public void prototypeTest() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        // 由于Phone是单例的，所以每次获取的都是同一个对象
        Phone phone = context.getBean(Phone.class);
        log.info("phone: {}", phone == context.getBean(Phone.class));
        // 由于Computer是多例的，所以每次获取的都是不同的对象
        Computer computer = context.getBean(Computer.class);
        log.info("computer: {}", computer == context.getBean(Computer.class));
        // 由于Phone只会依赖注入一次并初始化，所以即使Computer是多例的，但是每次获取的都是同一个对象
        // 如果要实现每次多例获取，调用getBean()来获取，或者使用@Lookup注解
        Computer computer1 = phone.getComputer();
        Computer computer2 = phone.getComputer();
        log.info("从phone中获取的computer: {}", computer1 == computer2);
    }


}
