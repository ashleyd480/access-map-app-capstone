package com.example.backendspringcode.service;

import com.example.backendspringcode.dto.UserUpdateDTO;
import com.example.backendspringcode.model.User;
import com.example.backendspringcode.repository.IUserRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    IUserRepository iUserRepository;

    /* ------- CREATE --------
     * Create a new user (when user creates an account) */

    public User addUser(User userToAdd) {
        try {
            return iUserRepository.save(userToAdd);
        } catch (Exception e) {
            throw new RuntimeException("Failed to add user: " + e.getMessage());
        }
    }
    /* Create a list of Users for testing purposes */

    /* Create a list of users for seeding test purposes */

    public List<User> addUsers(List<User> usersToAdd) {
        try {
            return iUserRepository.saveAll(usersToAdd);
        } catch (Exception e) {
            throw new RuntimeException("Failed to add users: " + e.getMessage());

        }
    }



    // to ensure  user seeding worked
    public List<User> getAllUsers() {
        try {
            return iUserRepository.findAll();
        }
        catch (Exception e) {
            throw new RuntimeException("Failed to get all users: " + e.getMessage());
        }
    }

    //  Get user by username
    public User getUserByUsername(String username) {
        try {
            return iUserRepository.findByUsername(username).orElse(null);
        } catch (Exception e) {
            throw new RuntimeException("Failed to get user by username: " + e.getMessage());
        }
    }

    /* ------- UPDATE --------
     * Update user info by id (if user updates his account)*/
    /* First runtime just checks if the optional user exists and the catch to see if anything else goes wrong */

    public User updateUserByUsername(String username, UserUpdateDTO userUpdateDTO) {
        try {
            // Find the existing user by id
            User existingUser = iUserRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("User with " + username + " does not exist."));
            // Create a new instance of ModelMapper
            ModelMapper modelMapper = new ModelMapper();
            // Map the properties from editedUser to existingUser
            modelMapper.getConfiguration().setSkipNullEnabled(true);
//             excludes null values - i.e. if user not want to update a field
            modelMapper.map(userUpdateDTO, existingUser);

            // Save the updated user
            return iUserRepository.save(existingUser);

        } catch (Exception e) {
            throw new RuntimeException("Failed to update user by user id: " + e.getMessage());
        }
    }


    /* ------- DELETE--------
     * Delete user by id (if user closes his account)
     * boolean deleted means if our deleteUserById returns true, then we give it a variable of "deleted"
     * @Transactional annotation in Spring ensures that the annotated method runs within a transactional context b/c without this annotation Spring doesn't see any active transaction happening*/
    @Transactional
    public boolean deleteUserByUsername(String username) {
        try {
           iUserRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("User with " + username + " does not exist."));

            iUserRepository.deleteUserByUsername(username);
            return !iUserRepository.findByUsername(username).isPresent();

        } catch (Exception e) {
            throw new RuntimeException("Failed to delete user by username: " + e.getMessage());
        }
    }






}