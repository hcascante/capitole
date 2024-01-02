package com.capitole.application.service;

import com.capitole.domain.model.Price;
import com.capitole.infrastructure.controller.dto.PriceRequestDTO;
import com.capitole.infrastructure.controller.dto.PriceResponseDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PriceService {
    List<PriceResponseDTO> findApplicablePrices(PriceRequestDTO priceRequestDTO);
}
