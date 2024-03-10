package com.example.interpreter;

/**
 * @author zhangjw54
 */
public class Test {

    public static void main(String[] args) {
        Context context = new Context();
        TerminalExpression a = new TerminalExpression("a");
        TerminalExpression b = new TerminalExpression("b");
        TerminalExpression c = new TerminalExpression("c");
        context.add(a, 4);
        context.add(b, 8);
        context.add(c, 2);
        // new PlusOperation(a,b).interpreter(context)--->返回12
        // c.interpreter(context)--->2
        // MinusOperation(12,2)..interpreter(context)--->10
        // a + b - c
        Expression rule = new MinusOperation(
                new PlusOperation(
                        a, b),
                c);
        System.out.println(rule.interpreter(context));

        Context context1 = new Context();
        // 解析过后是MinusOperation
        Expression minus = context1.build("14+3-888");
        System.out.println(minus.interpreter(context1));
    }
}
