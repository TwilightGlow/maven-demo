package com.example.test;

import com.example.util.JwtTokenUtils;
import org.junit.jupiter.api.Test;

/**
 * @author Javen
 * @date 2022/4/1
 */
public class JwtTest {

    @Test
    public void testToken() throws Exception {
        String token = JwtTokenUtils.createToken("Javen");
        System.out.println(token);
        String verifyToken = JwtTokenUtils.verifyToken(token);
        System.out.println(verifyToken);
    }
}
