package com.example.singleton;

/**
 * @author Javen
 * @date 2022/3/16
 */
// 所有枚举类型的serialVersionUID都是0L, 枚举对象的序列化和反序列化有自己的一套机制。
// 序列化时，仅仅将枚举对象的name属性输出到结果中，枚举对象本身不进行序列化。
// 反序列化时则是通过：
// 1. 反射获得枚举的class对象。
// 2. 调用getEnumConstantsShared(),其中再调用getMethod()方法等到values()方法，返回一个保存了所有已创建的枚举对象数组。
// 3. 调用enumConstantDirectory()方法，用已得到的枚举对象数组构造一个Map，其中key为name，value为与之对应的枚举对象。
// 4. 调用java.lang.Enum的valueOf()方法根据名字查找枚举对象。
// 5. 反序列化完成。

// 反射创建参考Constructor源码，if ((clazz.getModifiers() & Modifier.ENUM) != 0)
public enum EnumSingleton1 {

    // 反射无法创建枚举类的对象, 反序列化生成的还是同一个对象。
    // 枚举单例类在静态代码块中进行初始化，类似饿汉式
    SINGLETON;

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static void main(String[] args) {
        EnumSingleton1 singleton = Enum.valueOf(EnumSingleton1.class, "SINGLETON");
        System.out.println(singleton);
        for (EnumSingleton1 value : EnumSingleton1.values()) {
            System.out.println(value);
        }
        for (EnumSingleton1 enumConstant : EnumSingleton1.class.getEnumConstants()) {
            System.out.println(enumConstant);
        }
    }
}
