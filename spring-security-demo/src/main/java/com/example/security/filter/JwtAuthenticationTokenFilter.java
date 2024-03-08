package com.example.security.filter;

import com.example.security.entity.LoginUser;
import com.example.security.entity.User;
import com.example.security.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * @author Javen
 * @date 2023/8/21
 */
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //获取token
        String token = request.getHeader("token");
        if (!StringUtils.hasText(token)) {
            //放行
            filterChain.doFilter(request, response);
            return;
        }
        //解析token
        String userid;
        try {
            Claims claims = JwtUtils.parseJWT(token);
            userid = claims.getSubject();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("token非法");
        }
        //从redis中获取用户信息
        String redisKey = "login:" + userid;
//        LoginUser loginUser = redisCache.getCacheObject(redisKey);
//        if(Objects.isNull(loginUser)){
//            throw new RuntimeException("用户未登录");
//        }
        User user = User.builder()
                .id(1L)
                .userName("Javen")
                .nickName("Javen666")
                //                .password("$2a$10$FkYplqaAYWGN.VuOjfoyQ.QhsgGOV9olwM/Eom.OMZQALXyNcskSq")
                .password("$2a$10$YSUIUaFdwzgBjKn5xdFy1eniKFW9AcqUtWDH5Gxz93UEdltDmFVC2")
                .status("启用").build();
        List<String> authList = new ArrayList<>(Arrays.asList("ROLE_test", "ROLE_test1", "ROLE_test2"));
        LoginUser loginUser = new LoginUser();
        loginUser.setUser(user);
        loginUser.setPermissions(authList);
        //存入SecurityContextHolder
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginUser, null, loginUser.getAuthorities());
        // 这行十分重要，后面需要用到authenticationToken中的密码进行校验
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        //放行
        filterChain.doFilter(request, response);
    }
}
