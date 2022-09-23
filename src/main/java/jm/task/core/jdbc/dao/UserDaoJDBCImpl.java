package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

// Вопрос: зачем удалять String с запросами, какая конвенция запрещает так делать?
// во всех примерах, что я видел, пишут именно так: сперва стринга с запросом,
// а затем ее передают в метод. И на мой взгляд это смотрится более лаконично (могу ошибаться)
// Прошу вас прокомментировать этот момент в ревью.


public class UserDaoJDBCImpl implements UserDao {

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try (Connection connection = Util.getSQLConnection();

        ) {
            Statement statement = connection.createStatement();
            statement.execute("CREATE TABLE IF NOT EXISTS usersTable" +
                              "(id INT primary key auto_increment," +
                              "name VARCHAR(50)," +
                              "lastname VARCHAR(50)," +
                              "age TINYINT)"
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void dropUsersTable() {
        try (Connection connection = Util.getSQLConnection();
             Statement statement = connection.createStatement()
        ) {
            statement.execute("DROP TABLE IF EXISTS usersTable");
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public void saveUser(String name, String lastName, byte age) {
        try (Connection connection = Util.getSQLConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO usersTable " +
                     "(name, lastname, age)" +
                     "VALUES (?, ?, ?);")
        ) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public void removeUserById(long id) {
        try (Connection connection = Util.getSQLConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM usersTable " +
                     "WHERE id = ?;")
        ) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        User user = new User();

        try (Connection connection = Util.getSQLConnection();
             Statement statement = connection.createStatement()
        ) {
            ResultSet resultSet = statement.executeQuery("SELECT name, lastname, age " +
                                                         "FROM usersTable"); // Зачем закрывать ResultSet, если он
            while (resultSet.next()) {                                       // иерархически закрывается после
                user.setName(resultSet.getString("name"));      // закрытия connection?
                user.setLastName(resultSet.getString("lastname"));
                user.setAge(resultSet.getByte("age"));
                users.add(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return users;
    }

    public void cleanUsersTable() {
        try (Connection connection = Util.getSQLConnection();
             Statement statement = connection.createStatement()
        ) {
            statement.executeUpdate("TRUNCATE TABLE usersTable;");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
