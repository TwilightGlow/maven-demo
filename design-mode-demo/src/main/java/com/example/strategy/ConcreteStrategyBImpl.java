package com.example.strategy;

/**
 * @author Javen
 * @date 2022/3/23
 */
public class ConcreteStrategyBImpl implements IStrategy {

    @Override
    public double calculate(double price) {
        System.out.print("儿童票：");
        return price - 10;
    }
}
