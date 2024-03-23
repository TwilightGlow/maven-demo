package com.example.bean;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @author zhangjw54
 */
@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class Computer extends Product {
    public Computer() {
        this.setName("computer");
        this.setPrice(5000);
        System.out.println("Computer constructor...");
    }
}
