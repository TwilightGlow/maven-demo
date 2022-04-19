package com.example.template;

/**
 * @author Javen
 * @date 2022/2/1
 */
public class AbstractParentImpl extends AbstractParent {

    @Override
    public void method2() {
        super.method2();
        System.out.println("method2()在子类中扩展了！！");
    }

    @Override
    protected void method3() {
        System.out.println("method3()在子类中实现了！！");
    }
}
