package com.example.backendspringcode.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer reviewId;

    @NotNull(message = "Rating is required")
    private Integer rating;

    @NotBlank(message = "Comment cannot be blank")
    @Size(min = 5, message = "Comment must be at least 5 characters long")
    private String comment;
    @Column(updatable = false)
    @CreationTimestamp
    private LocalDateTime timeReviewCreated;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable= false)
    private User user;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "place_id", nullable= false)
    private Place place;


}

/* ------- Many to One with User --------
// Many reviews can belong to one user.
// @JoinColumn: user_id is the foreign key that references the user_id from User. Each review needs to be tied to a user (nullable= false).
// ^Hibernate then associates the user with that user_id to its respective reviews(s).


/* ------- Many to One with Place --------
// Many reviews can belong to one place.
// @JoinColumn: place_id is the foreign key that references the place_id from Place. Each review needs to be tied to a place (nullable= false).
// ^Hibernate then associates the place with that place_id to its respective reviews(s).
// JSON ignore added on this side to prevent circular reference- b/c instead we do want to see reviews under place


 */