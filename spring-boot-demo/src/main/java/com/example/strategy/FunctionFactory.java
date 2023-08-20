package com.example.strategy;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author Javen
 * @date 2023/8/6
 */
@Component
@RequiredArgsConstructor
public class FunctionFactory {

    private final Map<String, IFunction> functionMap;

    public IFunction getFunction(String mode) {
        return functionMap.get(mode);
    }
}
