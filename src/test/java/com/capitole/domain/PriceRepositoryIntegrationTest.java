package com.capitole.domain;

import com.capitole.AbstractIntegrationTest;
import com.capitole.infrastructure.entity.BrandEntity;
import com.capitole.infrastructure.entity.PriceEntity;
import com.capitole.infrastructure.entity.ProductEntity;
import com.capitole.domain.puerto.repository.PriceRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PriceRepositoryIntegrationTest extends AbstractIntegrationTest {
    @Autowired
    private PriceRepository priceRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @AfterEach
    public void afterEach() {
        priceRepository.deleteAll();
        jdbcTemplate.execute("ALTER TABLE PRICES ALTER COLUMN ID RESTART WITH 1");
        jdbcTemplate.execute("ALTER TABLE BRANDS ALTER COLUMN ID RESTART WITH 1");
    }

    @Test
    public void testFindApplicablePrices() {
        // Crear datos de prueba
        BrandEntity brand = new BrandEntity();
        brand.setId(1L);

        ProductEntity product = new ProductEntity();
        product.setId(35455L);

        LocalDateTime date = LocalDateTime.now(); // Puedes ajustar la fecha según tus necesidades

        // Guardar un precio en la base de datos
        PriceEntity price = new PriceEntity(1L, brand, date.minusDays(1L), date.plusDays(1L),
                1L, product, 1L, BigDecimal.valueOf(35L), "EUR");
        priceRepository.saveAndFlush(price);

        // Ejecutar la consulta
        Optional<PriceEntity> result = priceRepository.findApplicablePrices(brand.getId(), product.getId(), date);

        // Verificar el resultado
        assertTrue(result.isPresent());
        assertEquals(price.getId(), result.get().getId());
    }

    @Test
    public void testFindApplicablePrices_NoResult() {
        // Ejecutar la consulta (sin guardar ningún precio)
        Optional<PriceEntity> result = priceRepository.findApplicablePrices(1L, 35455L, LocalDateTime.now());

        // Verificar que no hay resultado
        assertTrue(result.isEmpty());
    }

    @Test
    public void testFindApplicablePrices_MultipleResults() {
        // Crear datos de prueba
        BrandEntity brand = new BrandEntity();
        brand.setId(1L);

        ProductEntity product = new ProductEntity();
        product.setId(35455L);

        LocalDateTime date = LocalDateTime.now();

        // Guardar múltiples precios en la base de datos con fechas que coincidan
        PriceEntity price1 = new PriceEntity(1L, brand, date.minusDays(1L), LocalDateTime.now().plusDays(1L),
                1L, product, 1L, BigDecimal.valueOf(35L), "EUR");
        PriceEntity price2 = new PriceEntity(2L, brand, date.minusDays(1L), LocalDateTime.now().plusDays(1L),
                1L, product, 2L, BigDecimal.valueOf(40L), "EUR");
        priceRepository.saveAll(List.of(price1, price2));

        // Ejecutar la consulta
        Optional<PriceEntity> result = priceRepository.findApplicablePrices(brand.getId(), product.getId(), date);

        // Verificar que se elige el precio con mayor prioridad
        assertTrue(result.isPresent());
        assertEquals(price2.getId(), result.get().getId());
    }

}
