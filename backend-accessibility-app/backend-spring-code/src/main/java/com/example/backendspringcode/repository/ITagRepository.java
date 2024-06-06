package com.example.backendspringcode.repository;

import com.example.backendspringcode.dto.TagDTO;
import com.example.backendspringcode.model.FeatureTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface ITagRepository extends JpaRepository <FeatureTag, Integer> {

//    List<FeatureTag> findFeatureTagsByPlacesPlaceId(Integer placeId);


    /* SELECT new clause allows you to create instances of a specified class (DTO) directly in the query. This is handy when you need to return a result that is not a direct entity from your database but a combination or subset of entity attributes.
    *Therefore, our return is a new TagDTO instance for each result
    * And each new TagDTO has a parameter of tagName which is the tag name from FeatureTag Entity; this is how we pass tagName to that TagDTO
     */

// to fetch relevant tags for each place

    @Query("SELECT t FROM FeatureTag t JOIN t.places p WHERE p.placeId = :placeId GROUP BY t")
    Optional <List<FeatureTag> >findFeatureTagsByPlaceId(@Param("placeId") Integer placeId);


    @Query("SELECT new com.example.backendspringcode.dto.TagDTO(t.tagName) " +
            "FROM FeatureTag t " +
            "JOIN t.places p " +
            "WHERE p.placeId = :placeId " +
            "GROUP BY t.tagName")
    Optional <List<TagDTO>> findTagsByPlaceId(@Param("placeId") Integer placeId);


}

/* We query from Feature Tag table and join it to the Tag table through the place Id
* We join the tag table to the places table via the collection of places tied to that Tag entity
* Use Inner Join so that only return tags associated to places " guy and girl share love in common so they move in, and they only put together in the house that they both love"
* Where p.placeId = :placeId (where the place Id is equal to the parameter of place Id
* Group By:  ensures only unique tags show up for each place, avoiding potential for duplicates
 */