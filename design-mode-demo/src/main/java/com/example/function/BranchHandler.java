package com.example.function;

/**
 * @author zhangjw54
 */
@FunctionalInterface
public interface BranchHandler {

    void handle(Runnable a, Runnable b);
}
