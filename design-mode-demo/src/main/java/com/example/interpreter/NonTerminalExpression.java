package com.example.interpreter;

import lombok.Getter;
import lombok.Setter;

/**
 * @author zhangjw54
 *
 * 文法中的每条规则对应于一个非终结表达式，非终结符表达式根据逻辑的复杂程度而增加，原则上每个文法规则都对应一个非终结符表达式
 *
 * 非终结符表达式（相当于树的树杈）：在这个例子中就是相加，相减的表达式，称为非终结符，这是非常形象的，
 * 因为当运算遇到这类的表达式的时候，必须先把非终结符的结果计算出来，犹如剥茧一般，一层一层的调用
 *
 * ！！！在解释器模式中，最终交给解释器运行的是终结表达式，因此非终结表达式的作用就是把终结表达式串联起来
 */
@Getter
@Setter
public abstract class NonTerminalExpression implements Expression {

    private final Expression e1;
    private final Expression e2;

    public NonTerminalExpression(Expression e1, Expression e2) {
        this.e1 = e1;
        this.e2 = e2;
    }

}
