package com.example.backendspringcode.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class UserUpdateDTO {

    @NotBlank(message = "Username is mandatory")
    @Size(min = 3, max = 50, message = "Username must be between 3 and 50 characters")
    private String username;


    @NotBlank(message = "New password is mandatory")
    @Size(min = 8, message = "New password must be at least 8 characters long")
    private String password;

    @NotBlank(message = "Email is mandatory")
    @Email(message = "Email should be valid")
    @Size(min = 3, max = 50, message = "Email must be between 3 and 50 characters")
    private String email;


    @NotBlank(message = "First name cannot be blank")

    private String firstName;
    @NotBlank(message = "Last name cannot be blank")

    private String lastName;
    @NotBlank(message = "City cannot be blank")

    private String city;
    @NotBlank(message = "State cannot be blank")

    private String state;



//    @NotBlank(message = "Old password is mandatory")
//    private String oldPassword;

}
