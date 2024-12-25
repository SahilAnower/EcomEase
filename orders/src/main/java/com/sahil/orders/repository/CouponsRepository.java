package com.sahil.orders.repository;

import com.sahil.orders.entity.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CouponsRepository extends JpaRepository<Coupon, Long> {
    Optional<Coupon> findByCode(String code);
}
