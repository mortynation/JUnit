package org.example.service;

import org.example.database.UserDatabase;
import org.example.entity.User;

import java.sql.SQLException;
import java.util.List;

public class UserService {

    UserDatabase db = new UserDatabase();

    public UserService() throws SQLException {
    }


    public void saveUser(User user){
        db.save(user);
    }

    public List<User> getUser(){
        return db.getUser();
    }
}
