package com.nonsuch1.jdbc.starter;

import com.nonsuch1.jdbc.starter.util.ConnectionManager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcRunner {
    public static void main(String[] args) throws SQLException {
        String sql = """
                SELECT * 
                FROM ticket
                """;
        try (Connection connection = ConnectionManager.open();
                Statement statement =
                        connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)) {
            System.out.println(connection.getTransactionIsolation());
            System.out.println(connection.getSchema());
            var executeResult = statement.executeQuery(sql);
            while (executeResult.next()) {
                System.out.println(executeResult.getLong("id"));
                System.out.println(executeResult.getString("passenger_no"));
                System.out.println(executeResult.getBigDecimal("cost"));
                System.out.println("------");
            }
        }
    }
}