package com.example.template;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

/**
 * @author Javen
 * @date 2022/2/1
 */
public abstract class AbstractJdbcTemplate {

    //template method
    public final Object execute(String sql) {
        // some logic here
        String statement = sql;
        String[] result = doInStatement(statement); //abstract method
        System.out.println("执行结果是： " + Arrays.toString(result));
        return result;
    }

    //implements in subclass
    protected abstract String[] doInStatement(String statement);
}
