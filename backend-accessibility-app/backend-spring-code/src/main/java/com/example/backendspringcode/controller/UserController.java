package com.example.backendspringcode.controller;

import com.example.backendspringcode.dto.ResponseDTO;
import com.example.backendspringcode.dto.UserUpdateDTO;
import com.example.backendspringcode.model.User;
import com.example.backendspringcode.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.backendspringcode.utils.BindingResultProcess.getBindingResponseEntity;

@RestController
@CrossOrigin(origins = "*")

@RequestMapping("/users")
public class UserController {
    @Autowired
    UserService userService;

    /* @Valid ensures we check the validation.
     * It throws exception when user fields are not correct and error message that we * annotate is sent to User
     * BindingResult result: holds result of validation such as errors
     * errorMap: for each field error add it to a hashmap with key of get field and value of get default message */



    /* ------- RETRIEVE -------- */

    // to ensure  user seeding worked
    @GetMapping
    public ResponseEntity<?> getAllUsers() {
        List<User> users = userService.getAllUsers();
        if (users.isEmpty()) {
            ResponseDTO responseDTO = new ResponseDTO();
            responseDTO.setError("No users found.");
            responseDTO.setHasError(true);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseDTO);
        }
        return ResponseEntity.ok(users);
    }


    // get user by username
    @GetMapping(params = {"username"})
    public ResponseEntity<?> getUserByUsername(@RequestParam String username) {
        User userMatchingUsername = userService.getUserByUsername(username);
        if (userMatchingUsername == null) {
            ResponseDTO responseDTO = new ResponseDTO();
            responseDTO.setError("Username does not exist.");
            responseDTO.setHasError(true);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseDTO);
        }
        return ResponseEntity.ok(userMatchingUsername);
    }


    /* ------- UPDATE --------
     * Update user info by id (if user updates his account)*/

    @PutMapping("{username}")

    public ResponseEntity<?> updateUserByUsername(@Valid @PathVariable String username, @Valid @RequestBody UserUpdateDTO userUpdateDto, BindingResult result) {
        if (result.hasErrors()) {
            return getBindingResponseEntity(result);
        } else {
            User updatedUser = userService.updateUserByUsername(username, userUpdateDto);
            return ResponseEntity.ok(updatedUser);
        }

    }


    /* ------- DELETE--------
     * Delete user by id (if user closes his account)
     * boolean deleted means if our deleteUserById returns true, then we give it a variable of "deleted" */
    @DeleteMapping("{username}")
    public ResponseEntity<?> deleteUserByUsername(@PathVariable String username) {
        boolean deleted = userService.deleteUserByUsername(username);
        if (!deleted) {
            ResponseDTO responseDTO = new ResponseDTO();
            responseDTO.setError("Your account was not able to be deleted.");
            responseDTO.setHasError(true);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseDTO);
        } else {
            ResponseDTO responseDTO = new ResponseDTO();
            responseDTO.setMessage("Your account was deleted successfully.");
            responseDTO.setHasError(false);
            return ResponseEntity.ok().body(responseDTO);
        }
    }

}


