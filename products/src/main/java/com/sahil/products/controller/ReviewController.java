package com.sahil.products.controller;


import com.sahil.products.constants.ProductsConstants;
import com.sahil.products.dto.ResponseDto;
import com.sahil.products.dto.ReviewDto;
import com.sahil.products.service.IReviewService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/reviews", produces = {MediaType.APPLICATION_JSON_VALUE})
@RequiredArgsConstructor
@Validated
public class ReviewController {

    private final IReviewService iReviewService;

    @PostMapping("/")
    public ResponseEntity<ResponseDto> createReview(@RequestBody @Valid ReviewDto reviewDto) {
        iReviewService.create(reviewDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(ProductsConstants.STATUS_201, ProductsConstants.REVIEW_MESSAGE_201));
    }

    @PutMapping("/")
    public ResponseEntity<ResponseDto> updateReview(@RequestBody @Valid ReviewDto reviewDto) {
        iReviewService.update(reviewDto);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseDto(ProductsConstants.STATUS_200, ProductsConstants.MESSAGE_200));
    }

}
