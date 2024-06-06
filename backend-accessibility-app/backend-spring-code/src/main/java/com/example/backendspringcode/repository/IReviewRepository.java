package com.example.backendspringcode.repository;

import com.example.backendspringcode.model.Review;
import com.example.backendspringcode.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IReviewRepository extends JpaRepository<Review, Integer> {

// to get average rating by place with JPQL
//    @Query("SELECT AVG(r.rating) FROM Review r WHERE r.place.placeId = :placeId")
//    Double calculateAverageRatingByPlaceId(Integer placeId);

    Optional <List<Review>> findReviewsByUserUsername(String username);


    //TODO revmoe?
    Optional < List<Review>> findReviewsByPlacePlaceId(Integer placeId);

}

