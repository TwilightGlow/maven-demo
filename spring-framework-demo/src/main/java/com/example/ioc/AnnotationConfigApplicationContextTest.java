package com.example.ioc;

import com.example.AppConfig;
import com.example.bean.Phone;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRegistration;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

/**
 * @author zhangjw54
 */
@Slf4j
public class AnnotationConfigApplicationContextTest {

    @Test
    public void annotationConfigApplicationContext() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.scan("com.example");
        context.refresh();
        Phone phone = context.getBean(Phone.class);
        log.info("phone -> {}", phone);
    }

    // 通过tomcat的方式部署
    static class MyWebAppInitializer implements WebApplicationInitializer {
        @Override
        public void onStartup(ServletContext servletContext) throws ServletException {
            // 创建 AnnotationConfigWebApplicationContext 对象
            AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
            // 注册配置类
            context.register(AppConfig.class);
            // 设置 ServletContext
            context.setServletContext(servletContext);
            // 注册 DispatcherServlet
            ServletRegistration.Dynamic servlet = servletContext.addServlet("dispatcher", new DispatcherServlet(context));
            servlet.setLoadOnStartup(1);
            servlet.addMapping("/");
            // 启动 Spring MVC 应用程序
            context.refresh();
        }
    }

}
