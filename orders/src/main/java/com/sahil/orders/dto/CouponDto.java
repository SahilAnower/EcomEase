package com.sahil.orders.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CouponDto {
    private Long id;
    @NotEmpty
    private String code;
    @NotEmpty
    private String type; // PERCENTAGE/FIXED
    @NotNull
    private Double value;
    private Boolean isActive = true;
}
