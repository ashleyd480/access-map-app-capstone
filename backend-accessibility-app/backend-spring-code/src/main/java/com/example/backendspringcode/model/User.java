package com.example.backendspringcode.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.amqp.RabbitConnectionDetails;

import java.util.ArrayList;
import java.util.List;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "\"User\"")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer userId;

    @NotBlank(message = "Username is mandatory")
    @Size(min = 3, max = 50, message = "Username must be between 3 and 50 characters")

    private String username;

    @NotBlank(message = "Password is mandatory")
    @Size(min = 8, message = "Password must be at least 8 characters long")

    private String password;

    @NotBlank(message = "First name is mandatory")
    private String firstName;

    @NotBlank(message = "Last name is mandatory")
    private String lastName;
    @NotBlank(message = "Email is mandatory")
    @Email(message = "Email should be valid")
    private String email;


    @NotBlank(message = "City cannot be blank")

    private String city;
    @NotBlank(message = "State cannot be blank")
    @Size(min = 2, max = 2, message = "State code must be exactly 2 characters")

    private String state;

    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Review> reviews = new ArrayList<>();

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Profile profile;



}

/* Adding CascadeType.ALL to the @OneToMany and @OneToOne annotations in your User entity allows you to delete a User instance even if it has associated Review and Profile instances. This behavior is due to the cascading effect of the CascadeType.ALL option.
*/


/*
User has city and state info for geolocation to show places near them
* -------  One to Many with Review --------
// One user can have multiple reviews.
// mappedBy: Reviews entity owns the relationship
// list of reviews mapped to each user by user field in Post.
// had to use @JSON ignore due to circular reference

*  -------  One to One with Profile  --------
// Each user has one profile
// CascadeType.ALL: for any actions taken on User should be taken on Profile; i.e. if User deleted, then we delete that Profile.


 */