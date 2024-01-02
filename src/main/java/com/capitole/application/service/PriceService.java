package com.capitole.application.service;

import com.capitole.infrastructure.controller.dto.PriceRequestDTO;
import com.capitole.infrastructure.controller.dto.PriceResponseDTO;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface PriceService {
    Optional<PriceResponseDTO> findApplicablePrice(PriceRequestDTO priceRequestDTO);
}
