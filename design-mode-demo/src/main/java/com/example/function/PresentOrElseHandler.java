package com.example.function;

import java.util.function.Consumer;

/**
 * @author zhangjw54
 */
@FunctionalInterface
public interface PresentOrElseHandler<T> {

    void presentOrHandle(Consumer<T> action, Runnable falseHandler);
}
