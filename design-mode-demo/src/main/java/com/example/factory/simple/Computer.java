package com.example.factory.simple;

/**
 * @author Javen
 * @date 2022/2/2
 */
public abstract class Computer {

    public final void category() {
        System.out.println("This is a computer");
    }

    protected abstract void name();
}
