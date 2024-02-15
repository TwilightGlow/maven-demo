package com.example.state.screen;

/**
 * @author Javen
 * @date 2024/2/15
 */
public enum ConcreteStateEnum {

    SATE_1 {
        @Override
        public void display() {
            System.out.println("STATE_1");
        }
    },
    SATE_2 {
        @Override
        public void display() {
            System.out.println("STATE_2");
        }
    },
    SATE_3 {
        @Override
        public void display() {
            System.out.println("STATE_3");
        }
    };


    public abstract void display();
}
