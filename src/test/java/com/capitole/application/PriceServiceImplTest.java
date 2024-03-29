package com.capitole.application;

import com.capitole.application.service.PriceServiceImpl;
import com.capitole.infrastructure.entity.BrandEntity;
import com.capitole.infrastructure.entity.ProductEntity;
import com.capitole.domain.puerto.repository.PriceRepository;
import com.capitole.infrastructure.entity.PriceEntity;
import com.capitole.domain.dto.PriceRequestDTO;
import com.capitole.domain.dto.PriceResponseDTO;
import com.capitole.infrastructure.mapper.PriceMapper;
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

    @Mock
    private PriceMapper priceMapper;

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

        PriceEntity mockPrice = new PriceEntity(1L, new BrandEntity(1L, "ZARA"), date.minusDays(1L), date.plusDays(1L), 1L, new ProductEntity(1L, "Remera"), 1L, BigDecimal.valueOf(35L), "EUR");
        PriceResponseDTO priceResponseDTO = new PriceResponseDTO(1L, 1L, 1L, date.minusDays(1L), date.plusDays(1L), BigDecimal.valueOf(35L));

        // Configurando el comportamiento del repositorio mock
        when(priceRepository.findApplicablePrices(any(), any(), any())).thenReturn(Optional.of(mockPrice));
        when(priceMapper.toPriceResponseDTO(mockPrice)).thenReturn(priceResponseDTO);

        // Ejecutando el método bajo prueba
        PriceResponseDTO result = priceService.findApplicablePrice(requestDTO);

        // Verificando el resultado
        assertEquals(new PriceResponseDTO(1L, 1L, 1L, date.minusDays(1L), date.plusDays(1L), BigDecimal.valueOf(35L)), result);

        // Verificando que el método del repositorio se llamó con los parámetros correctos
        verify(priceRepository, times(1)).findApplicablePrices(1L, 35455L, requestDTO.getDate());
    }
}