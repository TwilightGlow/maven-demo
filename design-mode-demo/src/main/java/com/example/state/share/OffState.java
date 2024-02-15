package com.example.state.share;

/**
 * @author Javen
 * @date 2024/2/15
 */
public class OffState extends State {

    public void on(Switch s) {
        System.out.println("打开！");
        s.setState(Switch.getState("on"));
    }

    public void off(Switch s) {
        System.out.println("已经关闭！");
    }
}
