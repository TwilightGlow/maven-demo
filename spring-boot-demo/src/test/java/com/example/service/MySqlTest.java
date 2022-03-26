package com.example.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Map;

/**
 * @author Javen
 * @date 2022/3/26
 */
@SpringBootTest
public class MySqlTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    public void test() {
        Map<String, Object> stringObjectMap = jdbcTemplate.queryForMap("select * from teacher where id = 5");
        System.out.println(stringObjectMap);
    }
}
