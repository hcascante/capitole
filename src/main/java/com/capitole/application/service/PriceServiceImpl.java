package com.capitole.application.service;

import com.capitole.domain.repository.PriceRepository;
import com.capitole.domain.model.Price;
import com.capitole.infrastructure.controller.dto.PriceRequestDTO;
import com.capitole.infrastructure.controller.dto.PriceResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PriceServiceImpl implements PriceService {

    private final PriceRepository priceRepository;

    @Autowired
    public PriceServiceImpl(PriceRepository priceRepository) {
        this.priceRepository = priceRepository;
    }

    @Override
    public Optional<PriceResponseDTO> findApplicablePrice(PriceRequestDTO priceRequestDTO) {
        return priceRepository.findApplicablePrices(priceRequestDTO.getBrandId(), priceRequestDTO.getProductId(), priceRequestDTO.getDate())
                .map(price -> new PriceResponseDTO(price.getProduct().getId(), price.getBrand().getId(),
                        price.getPriceList(), price.getStartDate(), price.getEndDate(), price.getPrice()));
    }

}