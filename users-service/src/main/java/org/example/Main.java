package org.example;

import org.example.entity.User;
import org.example.service.UserService;

import java.sql.SQLException;
import java.util.List;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) throws SQLException {
        UserService userService = new UserService();
//
//        userService.saveUser(new User("Nivaas", "Annadurai", "nannadurai@clarku.edu"));
//        userService.saveUser(new User("Revanth", "Ravi", "daterevanth@clarku.edu"))

        List<User> users = userService.getUser();
        for (User user : users){
            System.out.println(user);
        }

    }
}