package com.example.bean;

import org.springframework.beans.factory.support.MethodReplacer;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * @author zhangjw54
 */
public class PhoneReplace implements MethodReplacer {

    /**
     * obj：通过CGlib动态生成的被替换方法所在bean的子类。
     * method：被替换的方法对象。
     * args：执行方法需要的参数。
     */
    @Override
    public Object reimplement(Object obj, Method method, Object[] args) throws Throwable {
        System.out.println("执行的方法:" + method.getName());
        System.out.println("参数：" + Arrays.stream(args).toList());
        System.out.println("我是MethodReplacer......替换后的方法");
        // 调用原来的方法
        method.invoke(obj.getClass().getDeclaredConstructor().newInstance(), args);
        return obj;
    }
}
