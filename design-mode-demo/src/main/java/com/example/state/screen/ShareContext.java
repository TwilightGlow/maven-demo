package com.example.state.screen;

/**
 * @author Javen
 * @date 2024/2/15
 */
public class ShareContext {

    private ConcreteStateEnum currentState;
    private final ConcreteStateEnum normalState;
    private final ConcreteStateEnum largerState;
    private final ConcreteStateEnum largestState;

    public ShareContext() {
        this.normalState = ConcreteStateEnum.SATE_1;
        this.largerState = ConcreteStateEnum.SATE_2;
        this.largestState = ConcreteStateEnum.SATE_3;
        this.currentState = normalState;
        this.currentState.display();
    }

    public void setState(ConcreteStateEnum state) {
        this.currentState = state;
    }

    //单击事件处理方法，封转了对状态类中业务方法的调用和状态的转换
    public void onClick() {
        if (this.currentState == normalState) {
            this.setState(largerState);
            this.currentState.display();
        }
        else if (this.currentState == largerState) {
            this.setState(largestState);
            this.currentState.display();
        }
        else if (this.currentState == largestState) {
            this.setState(normalState);
            this.currentState.display();
        }
    }
}
