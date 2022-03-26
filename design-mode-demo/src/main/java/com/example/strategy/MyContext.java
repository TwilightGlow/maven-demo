package com.example.strategy;

/**
 * @author Javen
 * @date 2022/3/23
 */
public class MyContext {

    private double price;
    private IStrategy discount; //维持一个对抽象策略类的引用

    public void setPrice(double price) {
        this.price = price;
    }

    //注入一个折扣类对象
    public void setDiscount(IStrategy discount) {
        this.discount = discount;
    }

    public double getPrice() {
        //调用折扣类的折扣价计算方法
        return discount.calculate(this.price);
    }
}
