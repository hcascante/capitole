package com.capitole.infrastructure;

import com.capitole.AbstractIntegrationTest;
import com.capitole.infrastructure.entity.BrandEntity;
import com.capitole.infrastructure.entity.PriceEntity;
import com.capitole.infrastructure.entity.ProductEntity;
import com.capitole.domain.puerto.repository.PriceRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@AutoConfigureMockMvc
public class PriceControllerIntegrationTest extends AbstractIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PriceRepository priceRepository;

    @BeforeAll
    public void setUp() {
        BrandEntity brand = new BrandEntity(1L, "ZARA");
        ProductEntity product = new ProductEntity(35455L, "Remera");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        PriceEntity price1 = createPrice(1L, LocalDateTime.parse("2020-06-14 00:00:00", formatter),
                LocalDateTime.parse("2020-12-31 23:59:59", formatter), 1L, 0L, BigDecimal.valueOf(35.50), brand, product);
        PriceEntity price2 = createPrice(2L, LocalDateTime.parse("2020-06-14 15:00:00", formatter),
                LocalDateTime.parse("2020-06-14 18:30:00", formatter), 2L, 1L, BigDecimal.valueOf(25.45), brand, product);
        PriceEntity price3 = createPrice(3L, LocalDateTime.parse("2020-06-15 00:00:00", formatter),
                LocalDateTime.parse("2020-06-15 11:00:00", formatter), 3L, 1L, BigDecimal.valueOf(30.50),brand, product);
        PriceEntity price4 = createPrice(4L, LocalDateTime.parse("2020-06-15 16:00:00", formatter),
                LocalDateTime.parse("2020-12-31 23:59:59", formatter), 4L, 1L, BigDecimal.valueOf(38.95), brand, product);
        priceRepository.saveAll(List.of(price1, price2, price3, price4));
    }



    private PriceEntity createPrice(Long id, LocalDateTime startDate, LocalDateTime endDate, Long priceList, Long priority,
                                    BigDecimal price, BrandEntity brand, ProductEntity product) {

        return new PriceEntity(id, brand, startDate, endDate, priceList, product, priority, price, "EUR");
    }
    @Test
    public void testFindApplicablePrice1() throws Exception {

        // Test 1
        mockMvc.perform(MockMvcRequestBuilders.get("/prices")
                        .param("brandId", "1")
                        .param("productId", "35455")
                        .param("date", "2020-06-14T10:00:00")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.productId").value(35455))
                .andExpect(MockMvcResultMatchers.jsonPath("$.brandId").value(1));

    }
    @Test
    public void testFindApplicablePrice2() throws Exception {

        // Test 2
        mockMvc.perform(MockMvcRequestBuilders.get("/prices")
                        .param("brandId", "1")
                        .param("productId", "35455")
                        .param("date", "2020-06-14T16:00:00")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.productId").value(35455))
                .andExpect(MockMvcResultMatchers.jsonPath("$.brandId").value(1));

    }

    @Test
    public void testFindApplicablePrice3() throws Exception {
        // Test 3
        mockMvc.perform(MockMvcRequestBuilders.get("/prices")
                        .param("brandId", "1")
                        .param("productId", "35455")
                        .param("date", "2020-06-14T21:00:00")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.productId").value(35455))
                .andExpect(MockMvcResultMatchers.jsonPath("$.brandId").value(1));
    }

    @Test
    public void testFindApplicablePrice4() throws Exception {
        // Test 4
        mockMvc.perform(MockMvcRequestBuilders.get("/prices")
                        .param("brandId", "1")
                        .param("productId", "35455")
                        .param("date", "2020-06-15T10:00:00")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.productId").value(35455))
                .andExpect(MockMvcResultMatchers.jsonPath("$.brandId").value(1));
    }

    @Test
    public void testFindApplicablePrice5() throws Exception {
        // Test 5
        mockMvc.perform(MockMvcRequestBuilders.get("/prices")
                        .param("brandId", "1")
                        .param("productId", "35455")
                        .param("date", "2020-06-16T21:00:00")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.productId").value(35455))
                .andExpect(MockMvcResultMatchers.jsonPath("$.brandId").value(1));
    }
}
