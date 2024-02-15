package com.example.state.share;

/**
 * @author Javen
 * @date 2024/2/15
 */
public abstract class State {

    public abstract void on(Switch s);
    public abstract void off(Switch s);
}
