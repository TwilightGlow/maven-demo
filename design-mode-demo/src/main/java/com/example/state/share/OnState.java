package com.example.state.share;

/**
 * @author Javen
 * @date 2024/2/15
 */
public class OnState extends State {
    public void on(Switch s) {
        System.out.println("已经打开！");
    }

    public void off(Switch s) {
        System.out.println("关闭！");
        s.setState(Switch.getState("off"));
    }

}
