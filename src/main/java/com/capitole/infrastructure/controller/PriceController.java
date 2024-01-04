package com.capitole.infrastructure.controller;

import com.capitole.application.service.PriceService;
import com.capitole.domain.dto.PriceRequestDTO;
import com.capitole.domain.dto.PriceResponseDTO;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Optional;

@RestController
@Api(tags = "PriceEntity Controller")
public class PriceController {

    private final PriceService priceService;

    @Autowired
    public PriceController(PriceService priceService) {
        this.priceService = priceService;
    }

    @GetMapping("/prices")
    public ResponseEntity<PriceResponseDTO> findApplicablePrices(
            @RequestParam Long brandId,
            @RequestParam Long productId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime date) {
        return ResponseEntity.ok(priceService.findApplicablePrice(new PriceRequestDTO(date, productId, brandId)));
    }

}
