package com.example.interpreter.shop;

import lombok.Getter;

/**
 * @author zhangjw54
 */
@Getter
public class OrExpression implements Expression {

    private final Expression leftExpression;
    private final Expression rightExpression;


    public OrExpression(Expression leftExpression, Expression rightExpression) {
        this.leftExpression = leftExpression;
        this.rightExpression = rightExpression;
    }

    @Override
    public boolean interpret(Context context) {
        return leftExpression.interpret(context) || rightExpression.interpret(context);
    }
}
