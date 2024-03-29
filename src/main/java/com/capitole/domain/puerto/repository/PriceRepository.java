package com.capitole.domain.puerto.repository;

import com.capitole.infrastructure.entity.PriceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface PriceRepository extends JpaRepository<PriceEntity, Long> {
    @Query(value = "SELECT * FROM PRICES p WHERE p.BRAND_ID = :brandId " +
                                "AND p.PRODUCT_ID = :productId " +
                                "AND p.START_DATE <= :date " +
                                "AND p.END_DATE >= :date " +
                                "ORDER BY p.PRIORITY DESC LIMIT 1", nativeQuery = true)
    Optional<PriceEntity> findApplicablePrices(@Param("brandId") Long brandId,
                                               @Param("productId") Long productId,
                                               @Param("date") LocalDateTime date);

}