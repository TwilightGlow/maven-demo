package com.example.interpreter;

/**
 * @author zhangjw54
 */
public class PlusOperation extends NonTerminalExpression {

    public PlusOperation(Expression e1, Expression e2) {
        super(e1, e2);
    }

    @Override
    public int interpreter(Context context) {
        return getE1().interpreter(context) + getE2().interpreter(context);
    }
}
