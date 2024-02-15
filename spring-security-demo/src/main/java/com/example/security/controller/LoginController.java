package com.example.security.controller;

import com.example.security.entity.User;
import com.example.security.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Javen
 * @date 2023/8/21
 */
@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping("/user/login")
    public Map<String, Object> login(@RequestBody User user) {
        return loginService.login(user);
    }

    @GetMapping("/user/logout")
    public Map<String, Object> logout() {
        return loginService.logout();
    }

    @PostMapping("/hello")
    @PreAuthorize("hasAuthority('test1')")
//    @PreAuthorize("hasRole('test1')")
    public Map<String, Object> hello() {
        HashMap<String, Object> map = new HashMap<>();
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        map.put("authorization", authentication);
        map.put("principal", authentication.getPrincipal());
        map.put("authorities", authentication.getAuthorities());
        map.put("credentials", authentication.getCredentials());
        map.put("details", authentication.getDetails());
        map.put("userName", authentication.getName());
        return map;
    }
}
