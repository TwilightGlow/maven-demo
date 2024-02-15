package com.example.state.screen;

/**
 * @author Javen
 * @date 2024/2/15
 */
public enum LargerState implements State {

    LARGER_STATE;
    @Override
    public void display() {
        System.out.println("二倍大小！");
    }
}
