package com.example.backendspringcode.config;

import com.example.backendspringcode.model.User;
import com.example.backendspringcode.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserSeeder {

    @Autowired
    private UserService userService;


    public List<User> seedUsers() {
        List<User> usersToAdd = new ArrayList<>();
        /* initialize list of users
        * this list of user is then added with the bulk add method in service class
         */

        // Los Angeles users
        usersToAdd.add(createUser("user1", "password1", "John", "Doe", "john.doe@example.com", "Los Angeles", "CA"));
        usersToAdd.add(createUser("user2", "password2", "Jane", "Smith", "jane.smith@example.com", "Los Angeles", "CA"));
        usersToAdd.add(createUser("user3", "password3", "Michael", "Johnson", "michael.johnson@example.com", "Los Angeles", "CA"));


        // Austin users
        usersToAdd.add(createUser("user4", "password4", "David", "Brown", "david.brown@example.com", "Austin", "TX"));
        usersToAdd.add(createUser("user5", "password5", "Emily", "Wilson", "emily.wilson@example.com", "Austin", "TX"));
        usersToAdd.add(createUser("user6", "password9", "Olivia", "Brown", "olivia.brown@example.com", "Austin", "TX"));

        //Miami users

        usersToAdd.add(createUser("user7", "password6", "Daniel", "Taylor", "daniel.taylor@example.com", "Miami", "FL"));
        usersToAdd.add(createUser("user8", "password7", "Sophia", "Martinez", "sophia.martinez@example.com", "Miami", "FL"));
        usersToAdd.add(createUser("user9", "password9", "Matthew", "Garcia", "matthew.garcia@example.com", "Miami", "FL"));
        usersToAdd.add(createUser("user10", "password10", "Anita", "Gonzales", "anita.gonzalesa@example.com", "Miami", "FL"));

        return userService.addUsers(usersToAdd);
    }

    // helper method to create users
    /* Below we see a User constructor function called "createUser" with provided parameters
    * It is saying when a new user is created, you want to set its properties based on the parameters provided  */
    private User createUser(String username, String password, String firstName, String lastName, String email, String city, String state) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setCity(city);
        user.setState(state);
        return user;
    }
}


