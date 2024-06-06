package com.example.backendspringcode.controller;

import com.example.backendspringcode.dto.LoginDTO;
import com.example.backendspringcode.dto.ResponseDTO;
import com.example.backendspringcode.model.User;
import com.example.backendspringcode.service.AuthService;
import com.example.backendspringcode.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

import static com.example.backendspringcode.utils.BindingResultProcess.getBindingResponseEntity;


@RestController
@CrossOrigin(origins =  "*")
@RequestMapping("/users")
public class AuthController {
    @Autowired
    AuthService authService;
    @Autowired
    UserService userService;
    /* ------- CREATE --------
     * Create a new user (when user creates an account)
     * ? return type b/c either could return a string or the new user created */


    @PostMapping("signup")
    public ResponseEntity<?> authUserSignup(@Valid @RequestBody User userToAdd, BindingResult result) {
        if (result.hasErrors()) {
            return getBindingResponseEntity(result);
        } else if (authService.existsByUsername(userToAdd.getUsername())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Username already exists");
        } else {
            User newUserCreated = userService.addUser(userToAdd);
            return new ResponseEntity<>(newUserCreated, HttpStatus.CREATED);
        }
    }


    @PostMapping("login")
    public ResponseEntity<?> authUserLogin(@Valid @RequestBody LoginDTO loginDTO, BindingResult result) {
        if (result.hasErrors()) {
            return getBindingResponseEntity(result);
        } else if (!authService.existsByUsername(loginDTO.getUsername())) {
            ResponseDTO responseDTO = new ResponseDTO();
            responseDTO.setError("Username does not exist. Please create an account");
            responseDTO.setHasError(true);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseDTO);
        } else if (!authService.isPasswordValid(loginDTO.getUsername(), loginDTO.getPassword())) {
            ResponseDTO responseDTO = new ResponseDTO();
            responseDTO.setError("Password does not match.");
            responseDTO.setHasError(true);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(responseDTO);
        } else {
            ResponseDTO responseDTO = new ResponseDTO();
            responseDTO.setMessage("Login successful. Welcome, " + loginDTO.getUsername() + "!");
            responseDTO.setHasError(false);
            return ResponseEntity.ok().body(responseDTO);
        }
    }
}




