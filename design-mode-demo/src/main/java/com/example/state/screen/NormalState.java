package com.example.state.screen;

/**
 * @author Javen
 * @date 2024/2/15
 */
public enum NormalState implements State {

    NORMAL_STATE;
    public void display() {
        System.out.println("正常大小！");
    }

}
