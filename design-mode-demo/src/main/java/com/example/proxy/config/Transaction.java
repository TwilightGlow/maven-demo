package com.example.proxy.config;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhangjw54
 */
public class Transaction {

    public void a() {
        System.out.println("method a" + " : " + getClass());
        b();
    }

    public void b() {
        System.out.println("method b" + " : " + getClass());
    }

}

class Test {
    public static void main(String[] args) {
        // 被代理对象
        Transaction target = new Transaction();
        // Spring中的@Transactional代理模式
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(Transaction.class);
        enhancer.setCallback((MethodInterceptor) (obj, method, arguments, proxy) -> {
            String name = method.getName();
            if (name.equals("b")) {
                System.out.println("执行了事务处理: " + name);
            }
            return method.invoke(target, arguments);
        });
        Transaction transaction = (Transaction) enhancer.create();
        System.out.println(transaction.getClass());
        transaction.a();

        System.out.println("-----------------------");

        Map<String, Object> beans = new HashMap<>();
        Enhancer enhancer1 = new Enhancer();
        enhancer1.setSuperclass(Transaction.class);
        enhancer1.setCallback((MethodInterceptor) (obj, method, arguments, proxy) -> {
            String name = method.getName();
            if (name.equals("b")) {
                System.out.println("执行了事务处理: " + name);
                if (!beans.containsKey(name)) {
                    Object bean = proxy.invokeSuper(obj, arguments);
                    beans.put(name, bean);
                }
                return beans.get(name);
            }
            // 相当于在代理中使用super.xxx()调用，解决了自调用的问题
            return proxy.invokeSuper(obj, arguments);
//            return method.invoke(target, arguments);
//            return proxy.invoke(target, arguments);
        });
        Transaction transaction1 = (Transaction) enhancer1.create();
        System.out.println(transaction1.getClass());
        transaction1.a();

        // 第一种是组合模式，第二种是继承模式
    }
}

class ProxyTest {

    static class ExtendProxy extends Transaction {
        @Override
        public void a() {
            super.a();
        }
        @Override
        public void b() {
            System.out.println("b方法执行了事务");
            super.b();
        }
    }
    static class CompositeProxy extends Transaction {

        private final Transaction target;

        CompositeProxy(Transaction target) {
            this.target = target;
        }
        @Override
        public void a() {
            target.a();
        }
        @Override
        public void b() {
            System.out.println("b方法执行了书屋");
            target.b();
        }
    }

    public static void main(String[] args) {
        Transaction target = new Transaction();
        // 组合模式
        new CompositeProxy(target).a();
        System.out.println("----------------------");
        // 继承模式
        new ExtendProxy().a();
    }
}
