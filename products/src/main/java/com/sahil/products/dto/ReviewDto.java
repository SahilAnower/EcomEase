package com.sahil.products.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReviewDto {

   private Long id;

   @NotNull
   private Long userId;

   @NotNull
   private Long productId;

   @Min(1)
   @Max(5)
   private Integer rating;

   private String review;

   private Integer helpfulCount = 0;

}
