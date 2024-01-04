package com.capitole.application.service;

import com.capitole.domain.puerto.repository.PriceRepository;
import com.capitole.domain.dto.PriceRequestDTO;
import com.capitole.domain.dto.PriceResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

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