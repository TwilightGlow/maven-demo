package com.example.template;

/**
 * @author Javen
 * @date 2022/2/1
 */
public class AbstractJdbcTemplateImpl extends AbstractJdbcTemplate {

    @Override
    protected String[] doInStatement(String statement) {
        // 下层实现的业务逻辑
        String[] split = statement.split("");
        return split;
    }
}
