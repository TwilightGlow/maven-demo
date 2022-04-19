package com.example.model;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author Javen
 * @date 2022/4/4
 */
@Data
@Component
@PropertySource("classpath:staff.properties")
@ConfigurationProperties(prefix = "staff")
public class Staff {

    private String id;
    private String department;
    private Map<String, StaffAttribute> map;

    @Data
    public static class StaffAttribute {
        private String age;
        private String sex;
    }
}
