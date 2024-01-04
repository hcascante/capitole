package com.capitole.infrastructure.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "PRODUCTS")
@Data
public class ProductEntity {

    @Id
    @Column(name = "ID")
    private Long id;

    @Column(name = "PRODUCT_NAME")
    private String productName;

    public ProductEntity() {
    }

    public ProductEntity(Long id, String productName) {
        this.id = id;
        this.productName = productName;
    }
}
