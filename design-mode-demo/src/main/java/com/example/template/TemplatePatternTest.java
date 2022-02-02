package com.example.template;

import org.junit.jupiter.api.Test;

/**
 * @author Javen
 * @date 2022/2/1
 */
public class TemplatePatternTest {

    @Test
    public void test() {
        AbstractParentImpl abstractParent = new AbstractParentImpl();
        abstractParent.templateMethod();
    }

    @Test
    public void testJdbcTemplateAbstract() {
        String sql = "123456";
        AbstractJdbcTemplate abstractJdbcTemplate = new AbstractJdbcTemplateImpl();
        // 这里执行模板方法的是抽象类的引用，需要实现子类来完成业务逻辑
        abstractJdbcTemplate.execute(sql);
    }

    @Test
    public void testJdbcTemplateCallBack() {
        String sql = "123456";
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.query(sql, new StatementCallback() {
            @Override
            public String[] doInStatement(String stmt) {
                return stmt.split("");
            }
        });
    }

    @Test
    public void testJdbcTemplateWithInnerClass() {
        class QueryStatementCallback implements StatementCallback {
            @Override
            public String[] doInStatement(String stmt) {
                return stmt.split("");
            }
        }

        String sql = "123456";
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.query(sql, new QueryStatementCallback());
    }

}
