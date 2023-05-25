package com.parcial1.products.models;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String price;
    @Column(length = 1000, nullable = false)
    private String description;
    @Column(nullable = false)
    private String category;
    @Column(nullable = false)
    private String image;
}
