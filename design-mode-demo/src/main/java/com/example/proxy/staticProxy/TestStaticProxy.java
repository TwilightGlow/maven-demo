package com.example.proxy.staticProxy;

/**
 * @author Javen
 * @date 2022/3/16
 */
public class TestStaticProxy {

    public static void main(String[] args) {
        Cloth nikeCloth = new NikeClothImpl();
        ProxyClothFactory proxyCloth = new ProxyClothFactory(nikeCloth);
        proxyCloth.produceCloth();
    }
}
