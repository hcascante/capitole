package com.capitole.domain.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class PriceResponseDTO {

    private Long productId;
    private Long brandId;
    private Long priceList;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private BigDecimal price;

    public PriceResponseDTO(Long productId, Long brandId, Long priceList, LocalDateTime dateFrom, LocalDateTime dateTo, BigDecimal price) {
        this.productId = productId;
        this.brandId = brandId;
        this.priceList = priceList;
        this.startDate = dateFrom;
        this.endDate = dateTo;
        this.price = price;
    }

}
