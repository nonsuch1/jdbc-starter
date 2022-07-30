package com.nonsuch1.jdbc.starter;

import com.nonsuch1.jdbc.starter.util.ConnectionManager;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class jdbcRunner {
    public static void main(String[] args) throws SQLException {
        String sql = """
                UPDATE info
                SET data = 'TestTest'
                WHERE id = 5
                RETURNING *;
                """;
        try (Connection connection = ConnectionManager.open();
                Statement statement = connection.createStatement()) {
            System.out.println(connection.getTransactionIsolation());
            System.out.println(connection.getSchema());
            boolean executeResult = statement.execute(sql);
            System.out.println(executeResult);
            System.out.println(statement.getUpdateCount());
        }
    }
}
