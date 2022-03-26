package com.example.strategy;

/**
 * @author Javen
 * @date 2022/3/23
 */
public class Test {

    public static void main(String[] args) {
        MyContext myContext = new MyContext();
        myContext.setPrice(100.0);
        System.out.println("原始价格：" + 100.0);

        myContext.setDiscount(new ConcreteStrategyAImpl());
        System.out.println("A策略价格：" + myContext.getPrice());

        myContext.setDiscount(new ConcreteStrategyBImpl());
        System.out.println("B策略价格：" + myContext.getPrice());

        myContext.setDiscount(new ConcreteStrategyCImpl());
        System.out.println("C策略价格：" + myContext.getPrice());

        System.out.println("具体策略类的类名存储在配置文件中，通过工具类XMLUtil来读取配置文件并反射生成对象");
        myContext.setDiscount((ConcreteStrategyBImpl) XmlUtil.getBean());
        System.out.println("B策略价格：" + myContext.getPrice());
    }
}
