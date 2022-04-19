//package com.example.config;
//
//import com.example.interceptor.AuthenticationInterceptor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.CorsRegistry;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
///**
// * @author Javen
// * @date 2022/4/1
// */
//@Slf4j
//@Configuration
//public class GlobalWebMvcConfig implements WebMvcConfigurer {
//
//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        // 添加映射路径
//        registry.addMapping("/**")
//                // 放行哪些原始域
//                .allowedOriginPatterns("*")
//                // 是否发送Cookie信息
//                .allowCredentials(true)
//                // 放行哪些原始域(请求方式)
//                .allowedMethods("GET", "POST", "DELETE", "PUT", "OPTIONS", "HEAD")
//                // 放行哪些原始域(头部信息)
//                .allowedHeaders("*")
//                // 暴露哪些头部信息（因为跨域访问默认不能获取全部头部信息）
//                .exposedHeaders("Server","Content-Length", "Authorization", "Access-Token", "Access-Control-Allow-Origin","Access-Control-Allow-Credentials");
//    }
//
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        //添加权限拦截器
//        registry.addInterceptor(new AuthenticationInterceptor()).addPathPatterns("/**").excludePathPatterns("/static/**");
//    }
//}
