package com.example.proxy.cglib;

import com.example.proxy.MyInterface;
import com.example.proxy.Teacher;
import com.example.proxy.TestInterface;
import org.junit.jupiter.api.Test;
import org.springframework.cglib.proxy.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author Javen
 * @date 2022/3/12
 */
public class TestCglibProxy {

    // CGLIB不需要接口，不需要传入对象，被代理类需要有public构造器，生成该类的子类，因此CGLIB无法代理final方法。
    public static void main(String[] args) {
        // CGLIBProxy的执行方式有两种：
        //
        //1、Object obj = method.invoke(method.getDeclaringClass().newInstance(),args);
        //那么就是普通的反射调用，注意，不能用o做参数，因为会造成死循环。
        //
        //这种方式，和SDKProxy一样，因为CGLIB生成的代理类也把method对象存为类变量，所以执行效率也一样。
        //
        //2、Object obj = methodProxy.invokeSuper(o,objects); 这种方式，第一次执行时，会生成两个fast类，因此比sdkproxy慢，
        //第二次执行该方法，因为SDKProxy需要执行invoke，所以CGLIB更快。
        //当执行另一个方法时，因为SDKProxy需要生成methodAccessor并执行invoke，所以CGLIB更快。
        //CGLIB推荐用这种方式代理，被代理类不需要实现接口。另一种方式Proxy.newProxyInstance()需要提供接口和SDKProxy无异
        final Teacher target = new Teacher("Javen");
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(Teacher.class);
        enhancer.setCallback(new MethodInterceptor() {
            @Override
            // o是代理对象不是被代理对象!! method是调用的方法，object是参数列表，methodProxy是可以调用原方法和增强方法
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                System.out.println("before");
                method.setAccessible(true);
//                Object o1 = method.getDeclaringClass().getDeclaredConstructor().newInstance();
                Object result = method.invoke(target, objects);
//                Object result = methodProxy.invokeSuper(o, objects); // 这里的obj应该传入的是代理对象
//                Object result = methodProxy.invoke(target, objects); // 这里的obj应该传入的是被代理对象
                System.out.println("after");
                return result;
            }
        });
//        也可以用Enhancer.create(...)
        Teacher teacher = (Teacher) enhancer.create();
        // !!! 如果原方法是final类型的就不会被代理执行
        System.out.println(teacher.teach());
        // 反射调用
        try {
            Method test = Teacher.class.getDeclaredMethod("test");
            test.setAccessible(true);
            Object invoke = test.invoke(teacher);
            System.out.println(invoke);
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test() throws InvocationTargetException, IllegalAccessException {
        Object o = Proxy.newProxyInstance(TestCglibProxy.class.getClassLoader(), new Class[]{MyInterface.class}, new InvocationHandler() {
            @Override
            public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
                System.out.println("before");
                Object result = method.invoke((MyInterface) () -> "MyInterface", objects);
                System.out.println("after");
                return result;
            }
        });
        MyInterface result = (MyInterface) o;
        System.out.println(result.test());

        Teacher teacher = new Teacher();
        Method[] methods = Teacher.class.getMethods();
        Method method = null;
        try {
            method = Teacher.class.getDeclaredMethod("test");
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        method.setAccessible(true);
        Object invoke = method.invoke(teacher);
        System.out.println(invoke);
    }

    @Test
    public void test1() {
        TestInterface target = new Teacher();
        // cglib提供的这种方式必须基于接口代理，和SDKProxy方式相同，返回的类型必须是接口类型，不能是实现类类型
        TestInterface proxyInstance = (TestInterface) Proxy.newProxyInstance(TestCglibProxy.class.getClassLoader(),
                target.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
                System.out.println("before");
                Object result = method.invoke(target, objects);
                System.out.println("after");
                return result;
            }
        });
        System.out.println(proxyInstance.teach());
    }
}
