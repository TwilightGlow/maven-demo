package com.example.utils;

import com.example.controller.RsaToolsController;
import jakarta.annotation.PostConstruct;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

/**
 * @author zhangjw54
 */
@Service
public class ZService {


    @PostConstruct
    public void init() {
        System.out.println("AService init");
        ApplicationContext applicationContext = SpringUtils.getApplicationContext();
        RsaToolsController bean = applicationContext.getBean(RsaToolsController.class);
        System.out.println(bean);
        System.out.println("AService init end");
    }
}
