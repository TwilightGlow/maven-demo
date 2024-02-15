package com.example.state.screen;

/**
 * @author Javen
 * @date 2024/2/15
 */
public class Test {

    public static void main(String[] args) {
        Screen screen = new Screen();
        screen.onClick();
        screen.onClick();
        screen.onClick();

        ShareContext shareContext = new ShareContext();
        shareContext.onClick();
        shareContext.onClick();
        shareContext.onClick();
    }
}
