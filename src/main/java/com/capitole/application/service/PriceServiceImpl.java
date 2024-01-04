package com.capitole.application.service;

import com.capitole.domain.puerto.repository.PriceRepository;
import com.capitole.domain.dto.PriceRequestDTO;
import com.capitole.domain.dto.PriceResponseDTO;
import com.capitole.infrastructure.entity.PriceEntity;
import com.capitole.infrastructure.exceptions.PriceNotFoundException;
import com.capitole.infrastructure.mapper.PriceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PriceServiceImpl implements PriceService {

    private final PriceRepository priceRepository;
    private final PriceMapper priceMapper;

    @Autowired
    public PriceServiceImpl(PriceRepository priceRepository, PriceMapper priceMapper) {
        this.priceRepository = priceRepository;
        this.priceMapper = priceMapper;
    }

    @Override
    public PriceResponseDTO findApplicablePrice(PriceRequestDTO priceRequestDTO) {
        Optional<PriceEntity> applicablePrices = priceRepository.findApplicablePrices(priceRequestDTO.getBrandId(), priceRequestDTO.getProductId(), priceRequestDTO.getDate());
        if (applicablePrices.isPresent()) {
            return priceMapper.toPriceResponseDTO(applicablePrices.get());
        } else {
            throw new PriceNotFoundException("No se encontró un precio aplicable");
        }
    }

}