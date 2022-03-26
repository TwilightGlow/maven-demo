package com.example.proxy.staticProxy;

/**
 * @author Javen
 * @date 2022/3/16
 */
public class ProxyClothFactory implements Cloth {

    private Cloth cloth;

    public ProxyClothFactory(Cloth cloth) {
        this.cloth = cloth;
    }

    @Override
    public void produceCloth() {
        System.out.println("This is proxy factory start...");
        cloth.produceCloth();
        System.out.println("This is proxy factory end...");
    }
}
