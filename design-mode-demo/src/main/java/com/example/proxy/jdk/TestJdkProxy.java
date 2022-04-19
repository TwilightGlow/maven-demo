package com.example.proxy.jdk;

import com.example.proxy.MyInterface;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author Javen
 * @date 2022/3/12
 */
public class TestJdkProxy {

    // SDKProxy需要接口，需要传入对象，不需要无参构造器，生成该接口的实现类。
    // 生成代理类的源码，SDKProxy生成的是实现类，CGLIB生成的是子类，
    public static void main(String[] args) {
        MyInterface proxy = (MyInterface) Proxy.newProxyInstance(TestJdkProxy.class.getClassLoader(), new Class[]{MyInterface.class}, new InvocationHandler() {
            @Override
            // 当执行invoke方法时，SDKProxy的方式是
            // Object obj = method.invoke(this.target,args);
            // 该方法第一次执行是，需要生成 methodAccessor，这个生成很快。第16次执行时，需要生成字节码，会慢一下。
            // 第二次执行不需要生成MethodAccessor。但是执行另一方方法时，依然需要 methodAccessor。
            public Object invoke(Object proxy1, Method method, Object[] args1) throws Throwable {
                System.out.println("Before...");
                // 这里创建了MyInterface的匿名内部类
                Object result = method.invoke((MyInterface) () -> "MyInterface", args1);
                System.out.println("After...");
                return result;
            }
        });
        System.out.println(proxy.test());
    }
}
