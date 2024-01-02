package com.capitole.application.service;

import com.capitole.domain.repository.PriceRepository;
import com.capitole.domain.model.Price;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PriceServiceImpl implements PriceService {

    private final PriceRepository priceRepository;

    @Autowired
    public PriceServiceImpl(PriceRepository priceRepository) {
        this.priceRepository = priceRepository;
    }

    @Override
    public List<Price> findApplicablePrices(Long brandId, Long productId, LocalDateTime date) {
        return priceRepository.findApplicablePrices(brandId, productId, date);
    }

}