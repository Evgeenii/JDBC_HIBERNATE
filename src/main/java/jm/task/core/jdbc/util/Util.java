package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public final class Util {
    private static final String DB_DRIVER_PATH = "db.driver";
    private static final String DB_URL = "db.url";
    private static final String DB_USER = "db.username";
    private static final String DB_PASSWORD = "db.password";

    private Util() {

    }

    static {
        loadDriver();
    }

    public static SessionFactory getSession() {
        return buildSessionFactory();
    }

    private static SessionFactory buildSessionFactory() {
        SessionFactory sessionFactory = null;

        try {
            sessionFactory = new Configuration()
                    .addAnnotatedClass(User.class)
                    .buildSessionFactory();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return sessionFactory;
    }

    public static Connection getSQLConnection() {
        return openConnection();
    }

    private static void loadDriver() {
        try {
            Class.forName(PropertiesUtil.getPropertyValue(DB_DRIVER_PATH));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Driver Err");
            throw new RuntimeException(e);
        }
    }

    private static Connection openConnection() {
        try {
            return DriverManager.getConnection(
                    PropertiesUtil.getPropertyValue(DB_URL),
                    PropertiesUtil.getPropertyValue(DB_USER),
                    PropertiesUtil.getPropertyValue(DB_PASSWORD)
            );
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("SQL Err");
            throw new RuntimeException(e);
        }
    }
}
