package com.example.template;

import java.sql.SQLException;

/**
 * @author Javen
 * @date 2022/2/1
 */
@FunctionalInterface
public interface StatementCallback {

    String[] doInStatement(String stmt);
}
