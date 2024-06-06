package com.example.backendspringcode.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FeatureTag {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer tagId;
    private String tagName;
    // his would be the list of 5 current tags that we have in our database. Users can only select tags that system has (similar to Google Maps)

    @ManyToMany(mappedBy = "tags")
    @JsonIgnore
    private List<Place> places = new ArrayList<>();

    }




/* ------- Many to Many with Places --------
// A tag can be used on multiple places
// @JsonIgnore: to prevent circular reference since we are already showing the tags under Place
// (Note: JSON ignore does not affect me querying places by tagId since I use the logic of "return existingTag.gePlaces();" in my service method)
//mappedBy: Post entity is the owning side. Users create posts and then tags are associated with that post. The list of posts is mapped to the tag via the tagId in join table. */