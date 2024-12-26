package com.sahil.products.repository;

import com.sahil.products.dto.ProductQuantityPriceDto;
import com.sahil.products.entity.Product;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductsRepository extends JpaRepository<Product, Long> {
    List<Product> findByCategoryId(Long categoryId);

    @Query(value = "SELECT p.id AS id, p.available_quantity AS availableQuantity, p.price AS price " +
            "FROM product p " +
            "WHERE p.id IN (:productIds)", nativeQuery = true)
    List<ProductQuantityPriceDto> findProductDetailsByIdsNative(@Param("productIds") List<Long> productIds);

    @Modifying
    @Query(value = "UPDATE product p " +
            "SET p.available_quantity = p.available_quantity - :quantity " +
            "WHERE p.id = :productId AND p.available_quantity >= :quantity", nativeQuery = true)
    int updateProductInventory(@Param("productId") Long productId, @Param("quantity") Integer quantity);
}
