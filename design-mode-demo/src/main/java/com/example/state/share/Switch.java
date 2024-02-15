package com.example.state.share;

/**
 * @author Javen
 * @date 2024/2/15
 *
 * 这个例子中State在Switch中是静态的，因此多个开关共享状态
 *
 * 共享状态
 *       在有些情况下，多个环境对象可能需要共享同一个状态，
 *       如果希望在系统中实现多个环境对象共享一个或多个状态对象，那么需要将这些状态对象定义为环境类的静态成员对象。
 *
 * 如果某系统要求两个开关对象要么都处于开的状态，要么都处于关的状态，在使用时它们的状态必须保持一致，开关可以由开转换到关，也可以由关转换到开。
 */
public class Switch {

    private static State state,onState,offState; //定义三个静态的状态对象
    private final String name;

    public Switch(String name) {
        this.name = name;
        onState = new OnState();
        offState = new OffState();
        state = onState;
    }

    public void setState(State state) {
        Switch.state = state;
    }

    public static State getState(String type) {
        if (type.equalsIgnoreCase("on")) {
            return onState;
        }
        else {
            return offState;
        }
    }

    //打开开关
    public void on() {
        System.out.print(name);
        state.on(this);
    }

    //关闭开关
    public void off() {
        System.out.print(name);
        state.off(this);
    }
}
