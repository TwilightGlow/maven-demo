package com.example.template;

/**
 * @author Javen
 * @date 2022/2/1
 */
public abstract class AbstractParent {

    //模板方法
    public final void templateMethod() {
        method1();//父类方法
        method2();//勾子方法
        method3();//抽象方法
    }

    private void method1(){
        System.out.println("父类原本的业务逻辑");
    }

    public void method2(){
        System.out.println("父类默认实现，子类可覆盖");
    }

    protected abstract void method3();//子类负责实现业务逻辑
}
