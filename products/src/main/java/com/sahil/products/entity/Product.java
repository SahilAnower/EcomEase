package com.sahil.products.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Product extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private Double price;
    @Column(name = "available_quantity")
    private Integer availableQuantity;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id", insertable = false, updatable = false)
    private Category category;
    @Column(name = "category_id")
    private Long categoryId;
}
