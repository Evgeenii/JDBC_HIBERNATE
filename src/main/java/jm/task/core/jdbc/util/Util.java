package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public final class Util {
    // реализуйте настройку соединения с БД
    private static final String DB_DRIVER_PATH = "com.mysql.cj.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/jdbc_test";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "74Zumadu0996";

    static {
        loadDriver();
    }

    private Util() {

    }

    public static Connection getSQLConnection() {
        return openConnection();
    }

    private static void loadDriver() {
        try {
            Class.forName(DB_DRIVER_PATH);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Driver Err");
            throw new RuntimeException(e);
        }
    }

    private static Connection openConnection() {
        try {
            return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("SQL Err");
            throw new RuntimeException(e);
        }
    }
}
