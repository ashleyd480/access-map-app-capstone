package com.example.backendspringcode.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProfileDTO {
    private Integer numberPlacesReviewsContributedTo;
    private List<ReviewByUserDTO> userReviews;
}
