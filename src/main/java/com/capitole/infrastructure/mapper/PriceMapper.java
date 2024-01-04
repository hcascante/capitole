package com.capitole.infrastructure.mapper;

import com.capitole.domain.dto.PriceResponseDTO;
import com.capitole.infrastructure.entity.PriceEntity;
import org.springframework.stereotype.Service;

@Service
public class PriceMapper {

    public PriceResponseDTO toPriceResponseDTO(PriceEntity priceEntity) {

        return new PriceResponseDTO(priceEntity.getProduct().getId(), priceEntity.getBrand().getId(),
                priceEntity.getPriceList(), priceEntity.getStartDate(), priceEntity.getEndDate(), priceEntity.getPrice());
    }
}
