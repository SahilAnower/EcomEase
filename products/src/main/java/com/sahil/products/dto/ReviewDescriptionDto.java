package com.sahil.products.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReviewDescriptionDto {

    private Integer totalReviews = 0;
    private Double averageRating = 0.0;
    private HashMap<Integer, Integer> reviewCountByRating;
    private HashMap<Integer, Double> reviewPercentageByRating;

}
