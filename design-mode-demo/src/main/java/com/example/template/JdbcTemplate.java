package com.example.template;

import java.sql.SQLException;
import java.util.Arrays;

/**
 * @author Javen
 * @date 2022/2/1
 */
public class JdbcTemplate implements JdbcOperations {

    //template method
    @Override
    public Object query(String sql, StatementCallback action) {
        return execute(sql, action);
    }

    private Object execute(String sql, StatementCallback action) {
        // some logic here
        String stmt = sql;
        String[] result = action.doInStatement(stmt);
        System.out.println("执行结果是： " + Arrays.toString(result));
        return result;
    }
}
