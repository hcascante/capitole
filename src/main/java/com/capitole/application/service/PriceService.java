package com.capitole.application.service;

import com.capitole.domain.dto.PriceRequestDTO;
import com.capitole.domain.dto.PriceResponseDTO;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface PriceService {
    PriceResponseDTO findApplicablePrice(PriceRequestDTO priceRequestDTO);
}
