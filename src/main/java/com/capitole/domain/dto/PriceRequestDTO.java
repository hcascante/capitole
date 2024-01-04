package com.capitole.domain.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PriceRequestDTO {
    private LocalDateTime date;
    private Long productId;
    private Long brandId;

    public PriceRequestDTO(LocalDateTime date, Long productId, Long brandId) {
        this.date = date;
        this.productId = productId;
        this.brandId = brandId;
    }
}
