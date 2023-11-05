package com.example.prototype;

import java.io.IOException;

/**
 * @author zhangjw54
 */
public class Client {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        WeeklyLog log_previous = new WeeklyLog();  //创建原型对象
        log_previous.setName("张无忌");
        log_previous.setDate("第12周");
        log_previous.setContent("这周工作很忙，每天加班！");
        Attachment attachment = new Attachment();
        attachment.setName("第12周的附件");
        log_previous.setAttachment(attachment);
        System.out.println("****周报****" + log_previous.hashCode());
        System.out.println("****附件****" + log_previous.getAttachment().hashCode());
        System.out.println("周次：" + log_previous.getDate());
        System.out.println("姓名：" + log_previous.getName());
        System.out.println("内容：" + log_previous.getContent());
        System.out.println("附件：" + log_previous.getAttachment());
        System.out.println("--------------------------------");

        WeeklyLog log_new;
        log_new = log_previous.clone(); //调用克隆方法创建克隆对象
        log_new.setDate("第13周 -- 浅克隆");
        System.out.println("****周报****" + log_new.hashCode());
        System.out.println("****附件****" + log_new.getAttachment().hashCode());
        System.out.println(log_new.getAttachment() == log_previous.getAttachment());
        log_new.getAttachment().setName("附件浅浅克隆");
        System.out.println("周次：" + log_new.getDate());
        System.out.println("姓名：" + log_new.getName());
        System.out.println("内容：" + log_new.getContent());
        System.out.println("附件：" + log_new.getAttachment());

        // @Data修饰后，clone的对象和原来的对象具有相同的hashcode https://blog.csdn.net/weixin_47025878/article/details/129101631
        // 但是其地址已经不同了
        WeeklyLog log_new_deep;
        log_new_deep = log_previous.deepClone(); //调用深克隆方法创建克隆对象
        log_new_deep.setDate("第13周 -- 深克隆");
        System.out.println("****周报****" + log_new_deep.hashCode());
        System.out.println("****附件****" + log_new_deep.getAttachment().hashCode());
        System.out.println(log_new_deep.getAttachment() == log_previous.getAttachment());
        log_new_deep.getAttachment().setName("附件深克隆");
        System.out.println("周次：" + log_new_deep.getDate());
        System.out.println("姓名：" + log_new_deep.getName());
        System.out.println("内容：" + log_new_deep.getContent());
        System.out.println("附件：" + log_new_deep.getAttachment());

        // @Builder(toBuilder = true)可以也实现原型模式, 默认也是浅拷贝
        WeeklyLog build = log_previous.toBuilder().build();
        System.out.println(build == log_previous);
        System.out.println(build.getAttachment() == log_previous.getAttachment());

    }
}