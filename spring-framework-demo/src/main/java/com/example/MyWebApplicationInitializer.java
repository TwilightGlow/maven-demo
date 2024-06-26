// package com.example;
//
// import org.springframework.stereotype.Component;
// import org.springframework.web.WebApplicationInitializer;
// import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
// import org.springframework.web.servlet.DispatcherServlet;
//
// import javax.servlet.ServletContext;
// import javax.servlet.ServletRegistration;
//
// /**
// * @author Javen
// * @date 2022/2/16
// */
// @Component
// public class MyWebApplicationInitializer implements WebApplicationInitializer {
//
//    @Override
//    public void onStartup(ServletContext servletContext) {
//
//        // Load Spring web application configuration
//        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
//        context.register(AppConfig.class);
//
//        // Create and register the DispatcherServlet
//        DispatcherServlet servlet = new DispatcherServlet(context);
//        ServletRegistration.Dynamic registration = servletContext.addServlet("app", servlet);
//        registration.setLoadOnStartup(1);
//        registration.addMapping("/app/*");
//    }
// }
