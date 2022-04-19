package com.example.proxy.staticProxy;

/**
 * @author Javen
 * @date 2022/3/16
 */
public class NikeClothImpl implements Cloth {

    @Override
    public void produceCloth() {
        System.out.println("This is a Nike produced cloth");
    }
}
