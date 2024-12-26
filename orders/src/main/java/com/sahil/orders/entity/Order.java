package com.sahil.orders.entity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.*;
import lombok.*;

import java.util.Collections;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "orders")
public class Order extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long cartId;
    private Double totalAmount;
    private Double finalAmount;
    @Lob
    private String couponCodes;

    public List<String> getCouponCodes() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(couponCodes, List.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    public void setCouponCodes(List<String> couponCodes) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            this.couponCodes = objectMapper.writeValueAsString(couponCodes);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
