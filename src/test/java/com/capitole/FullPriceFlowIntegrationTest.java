package com.capitole;

import com.capitole.application.service.PriceServiceImpl;
import com.capitole.domain.model.Brand;
import com.capitole.domain.model.Price;
import com.capitole.domain.model.Product;
import com.capitole.domain.repository.PriceRepository;
import com.capitole.infrastructure.controller.dto.PriceRequestDTO;
import com.capitole.infrastructure.controller.dto.PriceResponseDTO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FullPriceFlowIntegrationTest {
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
        Brand brand = new Brand();
        brand.setId(1L);

        Product product = new Product();
        product.setId(35455L);

        LocalDateTime date = LocalDateTime.now();
        Price testPrice = new Price(1L, brand, date.minusDays(1L), date.plusDays(1L), 1L, product, 1L, BigDecimal.valueOf(35L), "EUR");
        this.priceRepository.saveAndFlush(testPrice);

        // Ejecutar el método bajo prueba
        PriceRequestDTO requestDTO = new PriceRequestDTO(
                LocalDateTime.now(),
                35455L,
                1L
        );
        Optional<PriceResponseDTO> result = priceService.findApplicablePrice(requestDTO);

        // Verificar el resultado
        assertEquals(Optional.of(new PriceResponseDTO(35455L, 1L, 1L, date.minusDays(1L), date.plusDays(1L), BigDecimal.valueOf(35L))), result);
    }
}
