package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Util {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/jdbc_test";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "74Zumadu0996";
    private static final String DB_DRIVER_PATH = "com.mysql.cj.jdbc.Driver";
    private static Connection connection;

    public static Connection getSQLConnection() {
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
        return connection;
    }

    public static void closeSQLConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
