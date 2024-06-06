package com.example.backendspringcode.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReviewDTO {
    @NotNull(message = "Rating is required")
    private Integer rating;

    @NotBlank(message = "Comment cannot be blank")
    @Size(min = 5, message = "Comment must be at least 5 characters long")
    private String comment;
}
