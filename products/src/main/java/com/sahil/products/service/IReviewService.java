package com.sahil.products.service;

import com.sahil.products.dto.ReviewDto;

public interface IReviewService {

    void create(ReviewDto reviewDto);

    void update(ReviewDto reviewDto);

}
