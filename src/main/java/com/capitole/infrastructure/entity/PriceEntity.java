package com.capitole.infrastructure.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "PRICES")
@Data
public class PriceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "BRAND_ID", referencedColumnName = "ID")
    private BrandEntity brand;

    @Column(name = "START_DATE")
    private LocalDateTime startDate;

    @Column(name = "END_DATE")
    private LocalDateTime endDate;

    @Column(name = "PRICE_LIST")
    private Long priceList;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "PRODUCT_ID", referencedColumnName = "ID")
    private ProductEntity product;

    @Column(name = "PRIORITY")
    private Long priority;

    @Column(name = "PRICE")
    private BigDecimal price;

    @Column(name = "CURR")
    private String currency;


    public PriceEntity() {

    }

    public PriceEntity(Long id, BrandEntity brand, LocalDateTime startDate, LocalDateTime endDate, Long priceList, ProductEntity product, Long priority, BigDecimal price, String currency) {
        this.id = id;
        this.brand = brand;
        this.startDate = startDate;
        this.endDate = endDate;
        this.priceList = priceList;
        this.product = product;
        this.priority = priority;
        this.price = price;
        this.currency = currency;
    }
}