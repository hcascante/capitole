package com.capitole.domain.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "BRANDS")
@Data
public class Brand {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "BRAND_NAME")
    private String brandName;

    public Brand() {

    }

    public Brand(Long id, String brandName) {
        this.id = id;
        this.brandName = brandName;
    }
}