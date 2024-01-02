package com.capitole.application.service;

import com.capitole.domain.model.Price;
import com.capitole.infrastructure.controller.dto.PriceRequestDTO;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public interface PriceService {
    List<Price> findApplicablePrices(PriceRequestDTO priceRequestDTO);
}
