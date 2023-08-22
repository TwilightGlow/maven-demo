package com.example.security.service;

import com.example.security.entity.User;

import java.util.Map;

/**
 * @author Javen
 * @date 2023/8/21
 */
public interface LoginService {

    Map<String, Object> login(User user);

    Map<String, Object> logout();
}
