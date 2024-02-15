package com.example.template;

/**
 * @author Javen
 * @date 2022/2/1
 */
// 模板方法模式是一种基于继承的代码复用技术，它是一种类行为型模式。
//
// 模板方法模式是结构最简单的行为型设计模式，在其结构中只存在父类与子类之间的继承关系。
// 通过使用模板方法模式，可以将一些复杂流程的实现步骤封装在一系列基本方法中，在抽象父类中提供一个称之为模板方法的方法来定义这些基本方法的执行次序，而通过其子类来覆盖某些步骤，从而使得相同的算法框架可以有不同的执行结果。
// 模板方法模式提供了一个模板方法来定义算法框架，而某些具体步骤的实现可以在其子类中完成。
// Spring中AbstractApplicationContext#refresh方法就是典型的模板方法模式
// refresh() 方法就是一个模板方法，它定义了方法的骨架，但是里面有的子方法没有在 AbstractApplicationContext 具体实现，而是交给了子类来实现。比如 postProcessBeanFactory、onRefresh()。
// postProcessBeanFactory 方法在 AbstractApplicationContext 是一个空方法！
// Springboot启动时，默认用的 ApplicationContext 是 AnnotationConfigServletWebServerApplicationContext，
// 它最终继承了AbstractApplicationContext，并且对postProcessBeanFactory空方法进行了实现，最终调用的也确实是这个方法。
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

    // 最简单的钩子方法就是空方法，或者是返回boolean的方法由子类实现
    public void method2(){
        System.out.println("父类默认实现，子类可覆盖");
    }

    protected abstract void method3();//子类负责实现业务逻辑
}
