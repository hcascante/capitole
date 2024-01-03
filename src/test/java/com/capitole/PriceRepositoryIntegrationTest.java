package com.capitole;

import com.capitole.domain.model.Brand;
import com.capitole.domain.model.Price;
import com.capitole.domain.model.Product;
import com.capitole.domain.repository.PriceRepository;
import com.capitole.infrastructure.conf.PriceTestConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(classes = PriceTestConfig.class)
public class PriceRepositoryIntegrationTest {
    @Autowired
    private PriceRepository priceRepository;


    @Test
    public void testFindApplicablePrices() {
        // Crear datos de prueba
        Brand brand = new Brand();
        brand.setId(1L);

        Product product = new Product();
        product.setId(35455L);

        LocalDateTime date = LocalDateTime.now(); // Puedes ajustar la fecha según tus necesidades

        // Guardar un precio en la base de datos
        Price price = new Price(1L, brand, date.minusDays(1L), date.plusDays(1L),
                1L, product, 1L, BigDecimal.valueOf(35L), "EUR");
        priceRepository.saveAndFlush(price);

        // Ejecutar la consulta
        Optional<Price> result = priceRepository.findApplicablePrices(brand.getId(), product.getId(), date);

        // Verificar el resultado
        assertTrue(result.isPresent());
        assertEquals(price.getId(), result.get().getId());
    }

    @Test
    public void testFindApplicablePrices_NoResult() {
        // Ejecutar la consulta (sin guardar ningún precio)
        Optional<Price> result = priceRepository.findApplicablePrices(1L, 35454L, LocalDateTime.now());

        // Verificar que no hay resultado
        assertTrue(result.isEmpty());
    }

    @Test
    public void testFindApplicablePrices_MultipleResults() {
        // Crear datos de prueba
        Brand brand = new Brand();
        brand.setId(1L);

        Product product = new Product();
        product.setId(35455L);

        LocalDateTime date = LocalDateTime.now();

        // Guardar múltiples precios en la base de datos con fechas que coincidan
        Price price1 = new Price(1L, brand, date.minusDays(1L), LocalDateTime.now().plusDays(1L),
                1L, product, 1L, BigDecimal.valueOf(35L), "EUR");
        Price price2 = new Price(2L, brand, date.minusDays(1L), LocalDateTime.now().plusDays(1L),
                1L, product, 2L, BigDecimal.valueOf(40L), "EUR");
        priceRepository.saveAll(List.of(price1, price2));

        // Ejecutar la consulta
        Optional<Price> result = priceRepository.findApplicablePrices(brand.getId(), product.getId(), date);

        // Verificar que se elige el precio con mayor prioridad
        assertTrue(result.isPresent());
        assertEquals(price2.getId(), result.get().getId());
    }

}
