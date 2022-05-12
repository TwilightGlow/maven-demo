package com.example.service.impl;

import com.example.service.INumOperate;

/**
 * @author Javen
 * @date 2022/5/12
 */
public class NumMultipleOperateImpl implements INumOperate {

    @Override
    public int exec(int num1, int num2) {
        return num1 * num2;
    }
}
