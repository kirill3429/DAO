package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException {
        UserService userService = new UserServiceImpl();
        userService.createUsersTable();
        User user1 = new User("Ivan", "Ivanov", (byte) 25);
        userService.saveUser(user1.getName(), user1.getLastName(), user1.getAge());
        System.out.printf("User с именем – %s добавлен в базу данных \n", user1.getName());

        User user2 = new User("Alexander", "Ivanov \n", (byte) 23);
        userService.saveUser(user2.getName(), user2.getLastName(), user2.getAge());
        System.out.printf("User с именем – %s добавлен в базу данных \n", user2.getName());
        User user3 = new User("Kirill", "Ivanov", (byte) 55);
        userService.saveUser(user3.getName(), user3.getLastName(), user3.getAge());
        System.out.printf("User с именем – %s добавлен в базу данных \n", user3.getName());
        User user4 = new User("Arslan", "Ivanov", (byte) 44);
        userService.saveUser(user4.getName(), user4.getLastName(), user4.getAge());
        System.out.printf("User с именем – %s добавлен в базу данных \n", user4.getName());

        List<User> users = userService.getAllUsers();

        for (User i : users) {
            System.out.println(i);
        }
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
