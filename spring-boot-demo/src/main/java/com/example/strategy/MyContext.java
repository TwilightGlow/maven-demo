package com.example.strategy;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @author Javen
 * @date 2023/8/6
 */
@Component
@RequiredArgsConstructor
public class MyContext {

    private final FunctionFactory factory;

    public Integer exec(String mode) {
        IFunction function = factory.getFunction(mode);
        return function.exec(1, 2);
    }
}
