package com.capitole.application;

import com.capitole.application.service.PriceServiceImpl;
import com.capitole.domain.model.Brand;
import com.capitole.domain.model.Product;
import com.capitole.domain.repository.PriceRepository;
import com.capitole.domain.model.Price;
import com.capitole.infrastructure.controller.dto.PriceRequestDTO;
import com.capitole.infrastructure.controller.dto.PriceResponseDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class PriceServiceImplTest {

    @Mock
    private PriceRepository priceRepository;

    @InjectMocks
    private PriceServiceImpl priceService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFindApplicablePrice() {
        // Mocking data
        PriceRequestDTO requestDTO = new PriceRequestDTO(
                LocalDateTime.now(),
                35455L,
                1L
        );

        LocalDateTime date = LocalDateTime.now();

        Price mockPrice = new Price(1L, new Brand(1L, "ZARA"), date.minusDays(1L), date.plusDays(1L), 1L, new Product(1L, "Remera"), 1L, BigDecimal.valueOf(35L), "EUR");

        // Configurando el comportamiento del repositorio mock
        when(priceRepository.findApplicablePrices(any(), any(), any())).thenReturn(Optional.of(mockPrice));

        // Ejecutando el método bajo prueba
        Optional<PriceResponseDTO> result = priceService.findApplicablePrice(requestDTO);

        // Verificando el resultado
        assertEquals(Optional.of(new PriceResponseDTO(1L, 1L, 1L, date.minusDays(1L), date.plusDays(1L), BigDecimal.valueOf(35L))), result);

        // Verificando que el método del repositorio se llamó con los parámetros correctos
        verify(priceRepository, times(1)).findApplicablePrices(1L, 35455L, requestDTO.getDate());
    }
}