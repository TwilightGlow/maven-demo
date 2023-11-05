package com.example.builder;

public class Test {

    public static void main(String[] args) {
        ActorBuilder devilBuilder = new DevilBuilder();
        ActorController controller = new ActorController();
        Actor devilActor = controller.construct(devilBuilder);
        System.out.println("外观：" + devilActor.getType());
        System.out.println("性别：" + devilActor.getSex());
        System.out.println("面容：" + devilActor.getFace());
        System.out.println("服装：" + devilActor.getCostume());
        System.out.println("发型：" + devilActor.getHairstyle());

        // 从配置文件中读取配置
        System.out.println("-----------------");
        ActorBuilder bean = (ActorBuilder) XmlUtil.getBean();
        Actor actor = controller.construct(bean);
        System.out.println("外观：" + actor.getType());
        System.out.println("性别：" + actor.getSex());
        System.out.println("面容：" + actor.getFace());
        System.out.println("服装：" + actor.getCostume());
        System.out.println("发型：" + actor.getHairstyle());

        // 省略Director，使用AbstractBuilder中的方法
        System.out.println("-----------------");
        Actor actor1 = bean.construct();
        System.out.println("外观：" + actor1.getType());
        System.out.println("性别：" + actor1.getSex());
        System.out.println("面容：" + actor1.getFace());
        System.out.println("服装：" + actor1.getCostume());
        System.out.println("发型：" + actor1.getHairstyle());
    }
}
