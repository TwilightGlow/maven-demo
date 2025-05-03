package com.example.algorithm.jdk;

import org.junit.jupiter.api.Test;

public class JdkVersion {

    @Test
    public void print() {
        String version = System.getProperty("java.version");
        System.out.println("JDK版本（详细）: " + version);

        String specVersion = System.getProperty("java.specification.version");
        System.out.println("JDK标准版本: " + specVersion);
    }
}
