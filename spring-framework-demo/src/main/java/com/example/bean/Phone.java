package com.example.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @author zhangjw54
 */
@Component
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class Phone extends Product {

    @Autowired
    private Computer computer;

    public Phone() {
        this.setName("phone");
        this.setPrice(1000);
        System.out.println("Phone constructor...");
    }

    public void call(String phoneNum) {
        System.out.println("Phone call..." + phoneNum);
    }

    public Computer getComputer() {
        return computer;
    }
}
