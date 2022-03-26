package com.example.aware;

import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * @author Javen
 * @date 2022/3/3
 */
@Component
public class TestEnvironmentAware implements EnvironmentAware {

    @Override
    public void setEnvironment(Environment environment) {
        System.out.println(environment);
    }
}
