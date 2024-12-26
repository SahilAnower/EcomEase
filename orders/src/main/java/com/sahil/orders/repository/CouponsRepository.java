package com.sahil.orders.repository;

import com.sahil.orders.entity.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CouponsRepository extends JpaRepository<Coupon, Long> {
    Optional<Coupon> findByCode(String code);


    List<Coupon> findByCodeIn (List<String> codes);
}
