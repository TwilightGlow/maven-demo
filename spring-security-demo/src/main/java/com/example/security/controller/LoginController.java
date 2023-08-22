package com.example.security.controller;

import com.example.security.entity.User;
import com.example.security.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
    public Map<String, Object> login(@RequestBody User user){
        return loginService.login(user);
    }

    @GetMapping("/user/logout")
    public Map<String, Object> logout(){
        return loginService.logout();
    }
}
