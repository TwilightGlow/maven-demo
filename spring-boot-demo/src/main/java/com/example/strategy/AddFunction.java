package com.example.strategy;

import org.springframework.stereotype.Service;

/**
 * @author Javen
 * @date 2023/8/6
 */
@Service("add")
public class AddFunction implements IFunction {

    @Override
    public int exec(int a, int b) {
        return a + b;
    }
}
