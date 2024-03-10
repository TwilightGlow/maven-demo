package com.example.interpreter.shop;

import lombok.Getter;

import java.util.List;

/**
 * @author zhangjw54
 */
@Getter
public class Context {

    private final List<String> cartItems;

    public Context(List<String> cartItems) {
        this.cartItems = cartItems;
    }
}
