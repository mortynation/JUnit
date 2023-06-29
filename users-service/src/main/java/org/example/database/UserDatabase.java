package org.example.database;

import org.example.entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDatabase {

    Connection connection = null;

    public UserDatabase() throws SQLException {
        connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/UserService", "postgres", "Mortynation@24");
    }
    public void save(User user){

        String query = "insert into users (first_name, last_name, email) values (?, ?, ?)";
        try {
            PreparedStatement st = connection.prepareStatement(query);
            st.setString(1, user.getFirstName());
            st.setString(2, user.getLastName());
            st.setString(3, user.getEmail());
            st.execute();
        } catch (SQLException e){
            throw new RuntimeException();
        }

    }

    public List<User> getUser() {

        List<User> users = new ArrayList<>();

        String query = "select first_name, last_name, email from users ";
        try {
            PreparedStatement st = connection.prepareStatement(query);
            ResultSet resultSet = st.executeQuery();
            while(resultSet.next()){
                User user = new User();
                user.setFirstName(resultSet.getString(1));
                user.setLastName(resultSet.getString(2));
                user.setEmail(resultSet.getString(3));
                users.add(user);
            }
        } catch (SQLException e){
            throw new RuntimeException();
        }
        return users;
    }
}
