package com.capitole.domain.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "PRODUCTS")
@Data
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "PRODUCT_NAME")
    private String productName;

    public Product() {
    }

    public Product(Long id, String productName) {
        this.id = id;
        this.productName = productName;
    }
}
