package com.parcial1.products.services;

import com.parcial1.products.models.Product;

import java.util.List;

public interface ProductService {
    Product getProductById(Long id);
    List<Product> allProducts();
    Product createProduct(Product product);
    Product updateProduct(Long id, Product product);
}
