package com.example.bean;

import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.stereotype.Component;

/**
 * @author zhangjw54
 */
@Component
public abstract class AbstractFactory {

    @Lookup("computer")
    public abstract Product createProduct();
}
