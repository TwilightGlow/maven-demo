package com.example.interpreter;

import lombok.Getter;
import lombok.Setter;

/**
 * @author zhangjw54
 *
 * 终结表达式一般实现与文法中的元素相关联的解释操作，通常一个解释器模式中只有一个终结表达式，但有多个实例，对应不同的非终结符。
 * 这个例子将元素映射为值
 *
 * ！！！最终交给解释器运行的是终结表达式
 */
@Getter
@Setter
public class TerminalExpression implements Expression {

    private final String variable;

    public TerminalExpression(String variable){
        this.variable = variable;
    }

    @Override
    public int interpreter(Context context) {
        Integer lookup = context.lookup(this);
        if (lookup == null) {
            return Integer.parseInt(variable);
        }
        return lookup;
    }
}
