package com.capitole;

import com.capitole.application.service.PriceServiceImpl;
import com.capitole.infrastructure.entity.BrandEntity;
import com.capitole.infrastructure.entity.PriceEntity;
import com.capitole.infrastructure.entity.ProductEntity;
import com.capitole.domain.puerto.repository.PriceRepository;
import com.capitole.domain.dto.PriceRequestDTO;
import com.capitole.domain.dto.PriceResponseDTO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FullPriceFlowIntegrationTest extends AbstractIntegrationTest {
    @Autowired
    private PriceRepository priceRepository;

    @Autowired
    private PriceServiceImpl priceService;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @AfterEach
    public void afterEach() {
        priceRepository.deleteAll();
        jdbcTemplate.execute("ALTER TABLE PRICES ALTER COLUMN ID RESTART WITH 1");
        jdbcTemplate.execute("ALTER TABLE BRANDS ALTER COLUMN ID RESTART WITH 1");
    }

    @Test
    public void testFindApplicablePriceIntegration() {
        // Crear datos de prueba
        BrandEntity brand = new BrandEntity();
        brand.setId(1L);

        ProductEntity product = new ProductEntity();
        product.setId(35455L);

        LocalDateTime date = LocalDateTime.now();
        PriceEntity testPrice = new PriceEntity(1L, brand, date.minusDays(1L), date.plusDays(1L), 1L, product, 1L, BigDecimal.valueOf(35L), "EUR");
        this.priceRepository.saveAndFlush(testPrice);

        // Ejecutar el método bajo prueba
        PriceRequestDTO requestDTO = new PriceRequestDTO(
                LocalDateTime.now(),
                35455L,
                1L
        );
        PriceResponseDTO result = priceService.findApplicablePrice(requestDTO);

        // Verificar el resultado
        assertEquals(new PriceResponseDTO(35455L, 1L, 1L, date.minusDays(1L), date.plusDays(1L), BigDecimal.valueOf(35L)), result);
    }
}
