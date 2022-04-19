package com.example.util;

import com.example.model.UserToken;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author Javen
 * @date 2022/4/1
 */
public class WebContextUtil {

    //本地线程缓存token
    private static final ThreadLocal<String> local = new ThreadLocal<>();

    public static void setUserToken(String content) {
        removeUserToken();
        local.set(content);
    }

    public static UserToken getUserToken() {
        if (local.get() != null) {
            UserToken userToken = null;
            try {
//                UserToken userToken = JSONObject.parseObject(local.get(), UserToken.class);
                userToken = new ObjectMapper().readValue(local.get(), UserToken.class);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            return userToken;
        }
        return null;
    }

    public static void removeUserToken() {
        if (local.get() != null) {
            local.remove();
        }
    }
}
