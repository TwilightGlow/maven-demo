package com.example.callback;

/**
 * @author Javen
 * @date 2022/1/8
 */
public class TestCallBack {

    public static void main(String[] args) {
        new SomeBusiness().compute(100, new ComputeCallBack() {
            @Override
            public void onComputeEnd() {
                System.out.println("Business执行完了");
            }
        });
    }
}
