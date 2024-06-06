package com.example.backendspringcode.service;

import com.example.backendspringcode.model.User;
import com.example.backendspringcode.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {
    @Autowired
    IUserRepository iUserRepository;


    /* ------- Sign Up Validation --------
     * Check if username exists */
    public boolean existsByUsername(String username) {
        try {
           return iUserRepository.existsByUsername(username);
        } catch (Exception e) {
            throw new RuntimeException("Failed to check if username exists: " + e.getMessage());
        }
    }

    /* ------- Sign In Validation --------
     * Check if password matches */
    public boolean isPasswordValid(String username, String password) {
        try {
       User existingUser = iUserRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("User with username " + username + " does not exist."));
        return existingUser.getPassword().equals(password);
    }
        catch (Exception e) {
            throw new RuntimeException("Failed to check if username exists: " + e.getMessage());
        }
    }

}