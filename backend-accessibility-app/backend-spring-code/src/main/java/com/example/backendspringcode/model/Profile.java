package com.example.backendspringcode.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer profileId;
//    private String bio;
//    private String profilePicture;
    private Integer numberPlacesReviewsContributedTo;

    @OneToOne
    @JoinColumn(name = "userId", unique= true)
    @JsonIgnore
    private User user;

}

/* -------  One to One with User --------
* Each profile is tied to one user.numberReviews (Integer)
* @JoinColumn: user_id is the foreign key (referencing userId from User) and each profile needs to be tied to a user (nullable= false) that is unique */