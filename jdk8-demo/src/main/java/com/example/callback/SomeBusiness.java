package com.example.callback;

/**
 * @author Javen
 * @date 2022/1/8
 */
public class SomeBusiness {

    public void compute(int n, ComputeCallBack computeCallBack) {
        for (int i = 0; i < n; i++) {
            System.out.println(i);
        }
        computeCallBack.onComputeEnd();
    }
}
