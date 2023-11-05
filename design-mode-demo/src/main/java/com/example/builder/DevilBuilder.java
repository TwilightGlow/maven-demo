package com.example.builder;

/**
 * @author zhangjw54
 */
public class DevilBuilder extends ActorBuilder {

    // 覆盖父类中的钩子方法
    public boolean isBareheaded() {
        return true;
    }

    public void buildType() {

        actor.setType("恶魔");

    }

    public void buildSex() {

        actor.setSex("妖");

    }

    public void buildFace() {

        actor.setFace("丑陋");

    }

    public void buildCostume() {

        actor.setCostume("黑衣");

    }

    public void buildHairstyle() {

        actor.setHairstyle("光头");

    }
}
