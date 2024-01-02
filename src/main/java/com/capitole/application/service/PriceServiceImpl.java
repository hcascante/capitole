package com.capitole.application.service;

import com.capitole.domain.repository.PriceRepository;
import com.capitole.domain.model.Price;
import com.capitole.infrastructure.controller.dto.PriceRequestDTO;
import com.capitole.infrastructure.controller.dto.PriceResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PriceServiceImpl implements PriceService {

    private final PriceRepository priceRepository;

    @Autowired
    public PriceServiceImpl(PriceRepository priceRepository) {
        this.priceRepository = priceRepository;
    }

    @Override
    public List<PriceResponseDTO> findApplicablePrices(PriceRequestDTO priceRequestDTO) {
        List<Price> applicablePrices = priceRepository.findApplicablePrices(priceRequestDTO.getBrandId(), priceRequestDTO.getProductId(), priceRequestDTO.getDate());
        return applicablePrices.stream().map(p -> new PriceResponseDTO(p.getProduct().getId(), p.getBrand().getId(),
                p.getPriceList(), p.getStartDate(), p.getEndDate(), p.getPrice())).collect(Collectors.toList());
    }

}