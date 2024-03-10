package com.example.interpreter.shop;

import lombok.Getter;

import java.util.List;

/**
 * @author zhangjw54
 */
@Getter
public class ProductExpression implements Expression {

    private final String productName;

    public ProductExpression(String productName) {
        this.productName = productName;
    }

    @Override
    public boolean interpret(Context context) {
        List<String> cartItems = context.getCartItems();
        return cartItems.contains(productName);
    }
}
