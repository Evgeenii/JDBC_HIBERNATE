package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Util implements AutoCloseable {
    private static Connection connection;

    static {
        String DB_URL = "jdbc:mysql://localhost:3306/jdbc_test";
        String DB_USER = "root";
        String DB_PASSWORD = "74Zumadu0996";
        String DB_DRIVER_PATH = "com.mysql.cj.jdbc.Driver";

        try {
            Class.forName(DB_DRIVER_PATH);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("SQL Err");

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Driver Err");

        }
    }

    public static Connection getSQLConnection() {
        return connection;
    }

    @Override
    public void close() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
