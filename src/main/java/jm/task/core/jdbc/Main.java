package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.Arrays;


public class Main {
    public static void main(String[] args) {
        UserServiceImpl userService = new UserServiceImpl();
        userService.createUsersTable();

        User user1 = new User("Petr", "Ivanov", (byte) 40);
        User user2 = new User("Ivan", "Ivanov", (byte) 30);
        User user3 = new User("Ivan", "Petrov", (byte) 20);
        User user4 = new User("Petr", "Petrov", (byte) 10);

        for (User user : Arrays.asList(user1, user2, user3, user4)) {
            userService.saveUser(user.getName(), user.getLastName(), user.getAge());
            System.out.println("User c именем " + user.getName() + " добавлен в базу данных");
        }

        System.out.println(userService.getAllUsers());
        userService.cleanUsersTable();
        userService.dropUsersTable();


    }
}
