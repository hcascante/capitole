package com.capitole.application.service;

import com.capitole.domain.model.Price;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public interface PriceService {
    List<Price> findApplicablePrices(Long brandId, Long productId, LocalDateTime date);
}
