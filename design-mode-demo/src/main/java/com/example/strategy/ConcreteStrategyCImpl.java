package com.example.strategy;

/**
 * @author Javen
 * @date 2022/3/23
 */
public class ConcreteStrategyCImpl implements IStrategy {

    @Override
    public double calculate(double price) {
        System.out.print("VIP票：");
        return price * 0.5;
    }
}
