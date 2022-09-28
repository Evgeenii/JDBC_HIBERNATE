package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AvailableSettings;
import org.hibernate.cfg.Configuration;

import java.util.Properties;

public final class Util {
    private static Util instance;

    private Util() {

    }

    public static Util getInstance() {
        if (instance == null) {
            instance = new Util();
        }
        return instance;
    }

    public SessionFactory getSession() {
        return buildSessionFactory();
    }

    private static SessionFactory buildSessionFactory() {
        SessionFactory sessionFactory = null;
        try {
            Properties props = new Properties();

            props.put(AvailableSettings.DRIVER, "com.mysql.cj.jdbc.Driver");
            props.put(AvailableSettings.URL, "jdbc:mysql://localhost:3306/jdbc_test");
            props.put(AvailableSettings.USER, "root");
            props.put(AvailableSettings.PASS, "74Zumadu0996");
            props.put(AvailableSettings.DIALECT, "org.hibernate.dialect.MySQL8Dialect");
            props.put(AvailableSettings.SHOW_SQL, "true");

            sessionFactory = new Configuration()
                    .addProperties(props)
                    .addAnnotatedClass(User.class)
                    .buildSessionFactory();

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return sessionFactory;
    }
}
