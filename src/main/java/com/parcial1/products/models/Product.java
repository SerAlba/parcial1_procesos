package com.parcial1.products.models;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String price;
    @Column(length = 1000)
    private String description;
    private String category;
    private String image;
}
