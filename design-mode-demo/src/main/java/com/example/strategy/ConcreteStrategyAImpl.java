package com.example.strategy;

/**
 * @author Javen
 * @date 2022/3/23
 */
public class ConcreteStrategyAImpl implements IStrategy {

    @Override
    public double calculate(double price) {
        System.out.print("学生票：");
        return price * 0.8;
    }
}
