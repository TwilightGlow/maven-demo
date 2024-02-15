package com.example.security.service.impl;

import com.example.security.entity.LoginUser;
import com.example.security.entity.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Javen
 * @date 2023/8/21
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 从数据库或Redis查询用户信息
        User user = User.builder()
                .id(1L)
                .userName("Javen")
                .nickName("Javen666")
//                .password("{noop}12345")
//                .password("$2a$10$FkYplqaAYWGN.VuOjfoyQ.QhsgGOV9olwM/Eom.OMZQALXyNcskSq")
                .password("$2a$10$YSUIUaFdwzgBjKn5xdFy1eniKFW9AcqUtWDH5Gxz93UEdltDmFVC2")
                .status("启用").build();

        // 从数据库查询权限信息
        List<String> authList = new ArrayList<>(Arrays.asList("test", "test1"));

        return new LoginUser(user, authList);
    }
}
