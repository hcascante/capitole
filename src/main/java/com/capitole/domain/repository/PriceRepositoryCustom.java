package com.capitole.domain.repository;

import com.capitole.domain.model.Price;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface PriceRepositoryCustom {
    Optional<Price> findApplicablePrices(Long brandId, Long productId, LocalDateTime date);
}
