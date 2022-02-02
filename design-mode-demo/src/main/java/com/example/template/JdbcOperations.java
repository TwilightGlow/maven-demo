package com.example.template;

/**
 * @author Javen
 * @date 2022/2/1
 */
public interface JdbcOperations {

    // 查询
    public Object query(String sql, StatementCallback action);
}
