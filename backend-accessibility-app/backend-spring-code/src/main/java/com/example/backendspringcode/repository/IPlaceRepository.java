package com.example.backendspringcode.repository;

import com.example.backendspringcode.dto.PlaceSearchResultsDTO;
import com.example.backendspringcode.model.Place;
import com.example.backendspringcode.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IPlaceRepository extends JpaRepository <Place, Integer> {




//    Optional<List<Place>> findPlacesByCity(String city);

    // so not case sensitive
    @Query("SELECT p FROM Place p WHERE LOWER(p.city) = LOWER(:city)")
    Optional<List<Place>> findPlacesByCity(@Param("city") String city);


        // get place info by id along with reviews and respective users
    /* If you frequently access associated entities or collections along with the main entity and need them to be available immediately without additional queries, eager loading can be beneficial */
    /* Researched this b/c without fetch we would iterate over all the place entities and access Review entities for each place --> lead to a significant number of SQL queries being executed, resulting in performance degradation and increased database load. */

    @Query("SELECT p FROM Place p LEFT JOIN FETCH p.reviews r LEFT JOIN FETCH r.user WHERE p.placeId = :placeId")
    Optional<Place> findPlaceWithReviewsById(@Param("placeId") Integer placeId);


    // for dynamic search
    // search just by keyword
    /* use case when which is like switch statement in java
    // we put asc / desc after END to know which column to sort by
    CASE expression is used to conditionally select the column to sort by, and the ORDER BY clause then sorts the rows based on the selected column.
    */

    @Query("SELECT new com.example.backendspringcode.dto.PlaceSearchResultsDTO( " +
            "p.placeId, p.placeName, p.streetAddress, p.city, p.state, p.zipCode, p.category, AVG(r.rating)) " +
            "FROM Place p LEFT JOIN p.reviews r " +
            "WHERE LOWER(p.placeName) LIKE %:keyword%  " +
            "GROUP BY p.placeId, p.placeName, p.streetAddress, p.city, p.state, p.zipCode, p.category " +
            "ORDER BY " +
            "CASE WHEN :sortField = 'name' AND :sortDirection = 'ascending' THEN p.placeName END ASC, " +
            "CASE WHEN :sortField = 'name' AND :sortDirection = 'descending' THEN p.placeName END DESC, " +
            "CASE WHEN :sortField = 'rating' AND :sortDirection = 'ascending' THEN AVG(r.rating) END ASC, " +
            "CASE WHEN :sortField = 'rating' AND :sortDirection = 'descending' THEN AVG(r.rating) END DESC")
    Optional<List<PlaceSearchResultsDTO>> findByKeyword(@Param("keyword") String keyword, @Param("sortField") String sortField, @Param("sortDirection") String sortDirection);

    /*I am noting that for future implementation a possibility is to just do dynamic search logic by keyword and location in the back, and sorting can be handled by the front end. This is because if say the user's keyword doesn't change- this can cause multiple unnecessary calls to the backend. :) */

    // search just by city
    @Query("SELECT new com.example.backendspringcode.dto.PlaceSearchResultsDTO(" +
            "p.placeId, p.placeName, p.streetAddress, p.city, p.state, p.zipCode, p.category, AVG(r.rating)) " +
            "FROM Place p " +
            "LEFT JOIN p.reviews r " +
            "WHERE LOWER(p.city) LIKE %:city% " +
            "GROUP BY p.placeId, p.placeName, p.streetAddress, p.city, p.state, p.zipCode, p.category " +
            "ORDER BY " +
            "CASE WHEN :sortField = 'name' AND :sortDirection = 'ascending' THEN p.placeName END ASC, " +
            "CASE WHEN :sortField = 'name' AND :sortDirection = 'descending' THEN p.placeName END DESC, " +
            "CASE WHEN :sortField = 'rating' AND :sortDirection = 'ascending' THEN AVG(r.rating) END ASC, " +
            "CASE WHEN :sortField = 'rating' AND :sortDirection = 'descending' THEN AVG(r.rating) END DESC")
    Optional<List<PlaceSearchResultsDTO>> findByCity(@Param("city") String city, @Param("sortField") String sortField, @Param("sortDirection") String sortDirection);

 // search just by city and keyword
    @Query("SELECT new com.example.backendspringcode.dto.PlaceSearchResultsDTO(" +
            "p.placeId, p.placeName, p.streetAddress, p.city, p.state, p.zipCode, p.category, AVG(r.rating)) " +
            "FROM Place p " +
            "LEFT JOIN p.reviews r " +
            "WHERE (LOWER(p.placeName) LIKE %:keyword% AND LOWER(p.city) LIKE %:city%) " +
            "GROUP BY p.placeId, p.placeName, p.streetAddress, p.city, p.state, p.zipCode, p.category " +
            "ORDER BY " +
            "CASE WHEN :sortField = 'name' AND :sortDirection = 'ascending' THEN p.placeName END ASC, " +
            "CASE WHEN :sortField = 'name' AND :sortDirection = 'descending' THEN p.placeName END DESC, " +
            "CASE WHEN :sortField = 'rating' AND :sortDirection = 'ascending' THEN AVG(r.rating) END ASC, " +
            "CASE WHEN :sortField = 'rating' AND :sortDirection = 'descending' THEN AVG(r.rating) END DESC")
    Optional<List<PlaceSearchResultsDTO>> findByKeywordAndCity(@Param("keyword") String keyword, @Param("city") String city, @Param("sortField") String sortField, @Param("sortDirection") String sortDirection);


//    @Query("SELECT new com.example.backendspringcode.dto.PlaceDTO(p) " +
//            "FROM Place p " +
//            "WHERE LOWER(p.city) = :city")
//    Optional< List<PlaceSearchResultsDTO>> findByCity(@Param("city") String city);
}


/* Select Place entities (p)
* LEFT JOIN FETCH p.reviews r: We are showing all place entities along with reviews (and we show all places regardless of if it has reviews)

LEFT JOIN FETCH r.user: retrieves the Review entities along with their associated User entities.
* Eager Fetching: The FETCH keyword tells JPA to fetch the associated entities eagerly in a single query.
* Where clause filters the place entities to only the one that matches the place Id
 */




//newest one with tags

//    @Query("SELECT new com.example.backendspringcode.dto.PlaceSearchResultsDTO(p.placeId, p.placeName, p.streetAddress, p.city, p.state, p.zipCode, p.category, " +
//            "STRING_AGG(t.tagId, ','), AVG(r.rating)) " +
//            "FROM Place p " +
//            "LEFT JOIN p.tags t " +
//            "LEFT JOIN p.reviews r " +
//            "WHERE p.city = :city " +
//            "GROUP BY p.placeId, p.placeName, p.streetAddress, p.city, p.state, p.zipCode, p.category")
//    List<PlaceSearchResultsDTO> findPlacesWithDetailsByCity(@Param("city") String city);

        // find tags tied to place
//        @Query("SELECT new com.example.backendspringcode.dto.TagWithClickCountDTO( t.tagName, t.clickCount) " +
//                "FROM FeatureTag t " +
//                "JOIN t.places p " +
//                "WHERE p.placeId = :placeId")
//        List<TagWithClickCountDTO> findTagsByPlaceId(@Param("placeId") Integer placeId);




