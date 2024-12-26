package com.sahil.orders.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Coupon extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "coupon_code")
    private String code;
    @Column(name = "coupon_type")
    private String type;
    @Column(name = "coupon_value")
    private Double value;
    private Boolean isActive;
}
