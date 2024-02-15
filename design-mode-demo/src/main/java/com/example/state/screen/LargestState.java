package com.example.state.screen;

/**
 * @author Javen
 * @date 2024/2/15
 */
public enum LargestState implements State {

    LARGEST_STATE;
    public void display() {
        System.out.println("四倍大小！");
    }
}
