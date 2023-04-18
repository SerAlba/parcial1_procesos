package com.parcial1.products.services;

import com.parcial1.products.models.Product;
import com.parcial1.products.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImp implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    public Product getProductById(Long id) {
        return productRepository.findById(id).get();
    }

    @Override
    public List<Product> allProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(Long id, Product product) {
        Product productBD = productRepository.findById(id).get();
        productBD.setTitle(product.getTitle());
        productBD.setPrice(product.getPrice());
        productBD.setDescription(product.getDescription());
        productBD.setCategory(product.getCategory());
        productBD.setImage(product.getImage());

        return productRepository.save(productBD);
    }
}
