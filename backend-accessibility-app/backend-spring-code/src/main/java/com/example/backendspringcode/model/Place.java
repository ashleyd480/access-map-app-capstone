package com.example.backendspringcode.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Place {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer placeId;
//
//    @NotBlank(message = "Place name cannot be blank")
    private String placeName;

//    @NotBlank(message = "Street address cannot be blank")
    private String streetAddress;

//    @NotBlank(message = "City cannot be blank")
    private String city;

//    @NotBlank(message = "State cannot be blank")
//    @Size(min = 2, max = 2, message = "State code must be exactly 2 characters")
    private String state;

//    @NotBlank(message = "Zip code cannot be blank")
    private String zipCode;

//    @NotBlank(message = "Category  cannot be blank")
    private String category;

//@JsonIgnore
    @OneToMany(mappedBy = "place", cascade = CascadeType.ALL)

    private List<Review> reviews = new ArrayList<>();



    @ManyToMany
    @JoinTable(
            name = "place_tags",
            joinColumns = @JoinColumn(name = "place_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )

    private List<FeatureTag> tags = new ArrayList<>();

}


 /* ------- One to Many with Review --------
* One place can have multiple reviews
* mappedBy: Review entity owns the relationship
* list of reviews mapped to each place by place field in Review.

------- Many to Many with FeatureTags--------
* A place  can have many FeatureTags.
* @JoinTable since we query more from places, and when we do query, the related FeatureTags will display.
* inverseJoinColumn: tag_id is the foreign key.
 */
