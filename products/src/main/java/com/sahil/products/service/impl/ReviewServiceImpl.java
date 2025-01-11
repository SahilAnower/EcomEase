package com.sahil.products.service.impl;

import com.sahil.products.dto.ReviewDto;
import com.sahil.products.entity.Review;
import com.sahil.products.exception.ProductUnavailableException;
import com.sahil.products.exception.ReviewUnavailableException;
import com.sahil.products.mapper.GenericMapper;
import com.sahil.products.repository.ReviewRepository;
import com.sahil.products.service.IReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements IReviewService {

    private final GenericMapper genericMapper;
    private final ReviewRepository reviewRepository;

    @Override
    public void create(ReviewDto reviewDto) {
        reviewRepository.save(genericMapper.reviewDtoToReview(reviewDto));
    }

    @Override
    public void update(ReviewDto reviewDto) {
        Long reviewId = reviewDto.getId();
        if (reviewId == null) throw new ReviewUnavailableException("Review id is null");
        Review review = reviewRepository.findById(reviewId).orElseThrow(() -> new ReviewUnavailableException("Review not found with id: " + reviewId));
        genericMapper.updateReviewFromReviewDto(reviewDto, review);
        reviewRepository.save(review);
    }
}
