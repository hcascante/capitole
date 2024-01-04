package com.capitole.infrastructure;

import com.capitole.domain.dto.PriceResponseDTO;
import com.capitole.infrastructure.entity.BrandEntity;
import com.capitole.infrastructure.entity.PriceEntity;
import com.capitole.infrastructure.entity.ProductEntity;
import com.capitole.infrastructure.mapper.PriceMapper;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PriceMapperTest {
    private final PriceMapper priceMapper = new PriceMapper();

    @Test
    public void testToPriceResponseDTO() {
        ProductEntity product = new ProductEntity();
        product.setId(1L);

        BrandEntity brand = new BrandEntity();
        brand.setId(1L);

        PriceEntity priceEntity = new PriceEntity();
        priceEntity.setProduct(product);
        priceEntity.setBrand(brand);
        priceEntity.setId(1L);
        LocalDateTime date = LocalDateTime.now();
        priceEntity.setStartDate(date.minusDays(1));
        priceEntity.setEndDate(date.plusDays(1));
        priceEntity.setPriceList(1L);
        priceEntity.setPrice(BigDecimal.valueOf(35L));

        // When
        PriceResponseDTO priceResponseDTO = priceMapper.toPriceResponseDTO(priceEntity);

        // Then
        assertEquals(date.minusDays(1), priceResponseDTO.getStartDate());
        assertEquals(date.plusDays(1), priceResponseDTO.getEndDate());
        assertEquals(1L, priceResponseDTO.getPriceList());
        assertEquals(BigDecimal.valueOf(35L), priceResponseDTO.getPrice());
    }
}
