package com.capitole.domain.repository;

import com.capitole.domain.model.Price;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PriceRepositoryCustom {
    List<Price> findApplicablePrices(Long brandId, Long productId, LocalDateTime date);
}
