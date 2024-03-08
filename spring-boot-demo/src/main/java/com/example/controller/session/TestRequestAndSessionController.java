package com.example.controller.session;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Date;
import java.util.stream.Collectors;

/**
 * @author Javen
 * @date 2023/8/5
 */
@RestController
public class TestRequestAndSessionController {

    @GetMapping("/v1.0/test_request")
    public String testRequest(HttpServletRequest request) {
        Principal userPrincipal = request.getUserPrincipal();
        System.out.println("userPrincipal: " + userPrincipal);
        Cookie[] cookies = request.getCookies();
        System.out.println("cookies: " + Arrays.stream(cookies).map(x -> x.getName() + " " + x.getPath() + " " + x.getValue() + " " + x.getMaxAge()).collect(Collectors.toList()));
        String authType = request.getAuthType();
        System.out.println("authType: " + authType);
        String contextPath = request.getContextPath();
        System.out.println("contextPath: " + contextPath);
        String method = request.getMethod();
        System.out.println("method: " + method);
        String requestURI = request.getRequestURI();
        System.out.println("requestURI: " + requestURI);
        // request的属性生命周期是一次请求
        System.out.println(request.getAttribute("aaa"));
        request.setAttribute("aaa", 123);
        System.out.println(request.getAttribute("aaa"));
        return request.toString();
    }

    @GetMapping("/v1.0/test_session")
    public String testSession(HttpSession httpSession) {
        long creationTime = httpSession.getCreationTime();
        System.out.println("creationTime: " + creationTime);
        // session的属性生命周期是一次会话，取决于后端的session过期时间，或者前端的cookie过期后也会创建新的session
        System.out.println(httpSession.getAttribute("bbb"));
        httpSession.setAttribute("bbb", 456);
        System.out.println(httpSession.getAttribute("bbb"));
        return httpSession.toString();
    }
}
