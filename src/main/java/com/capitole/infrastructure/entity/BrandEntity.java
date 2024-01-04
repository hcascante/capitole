package com.capitole.infrastructure.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "BRANDS")
@Data
public class BrandEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "BRAND_NAME")
    private String brandName;

    public BrandEntity() {

    }

    public BrandEntity(Long id, String brandName) {
        this.id = id;
        this.brandName = brandName;
    }
}