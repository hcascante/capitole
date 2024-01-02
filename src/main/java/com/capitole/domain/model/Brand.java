package com.capitole.domain.model;

import javax.persistence.*;

@Entity
@Table(name = "BRANDS")
public class Brand {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "BRAND_NAME")
    private String brandName;
}