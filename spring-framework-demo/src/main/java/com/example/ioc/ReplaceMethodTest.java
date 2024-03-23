package com.example.ioc;

import com.example.bean.Phone;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author zhangjw54
 */
@Slf4j
public class ReplaceMethodTest {

    @Test
    public void replaceMethod() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("replace-method.xml");
        Phone phone = context.getBean("phone", Phone.class);
        phone.call("12345");
    }
}
