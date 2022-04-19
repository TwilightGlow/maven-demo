package com.example.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;

import java.util.Date;

/**
 * @author Javen
 * @date 2022/4/1
 */
public class JwtTokenUtils {

    //定义token返回头部
    public static final String AUTH_HEADER_KEY = "Authorization";

    //token前缀
    public static final String TOKEN_PREFIX = "Bearer ";

    //签名密钥
    public static final String KEY = "q3t6w9z$C&F)J@NcQfTjWnZr4u7x";

    //有效期默认为 2hour
    public static final Long EXPIRATION_TIME = 1000L * 60 * 60 * 2;

    public static String createToken(String content) {
        return TOKEN_PREFIX + JWT.create()
                .withClaim("key1", "value1") // Jwt的payload可以自定义内容
                .withClaim("key2", "value2")
                .withSubject(content)
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .sign(Algorithm.HMAC512(KEY));
    }

    public static String verifyToken(String token) throws Exception {
        try {
            return JWT.require(Algorithm.HMAC512(KEY))
                    .build()
                    .verify(token.replace(TOKEN_PREFIX, ""))
//                    .getClaim("key1").asString();
                    .getSubject();
        } catch (TokenExpiredException e) {
            throw new Exception("token已失效，请重新登录", e);
        } catch (JWTVerificationException e) {
            throw new Exception("token验证失败！", e);
        }
    }
}
